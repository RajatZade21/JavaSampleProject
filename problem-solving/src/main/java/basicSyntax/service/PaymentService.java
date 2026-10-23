package basicSyntax.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Service;
import java.util.function.Supplier;

@Service
public class PaymentService {
    private final Retry paymentRetry;
    private final CircuitBreaker paymentCircuitBreaker;

    public PaymentService(
            RetryRegistry retryRegistry,
            CircuitBreakerRegistry circuitBreakerRegistry
                ) {
        this.paymentRetry = retryRegistry.retry("paymentService");
        this.paymentCircuitBreaker = circuitBreakerRegistry.circuitBreaker("payment");
    }

    /*
        Retry mechanism
     */
    public String processPayment() {
        Supplier<String> operation = Retry.decorateSupplier(
                paymentRetry,
                () -> callPaymentApi()
        );

        return operation.get();
    }

    /*
        CircuitBreaker mechanism
     */
    public String processPayment2() {
        Supplier<String> operation = CircuitBreaker.decorateSupplier(
                paymentCircuitBreaker,
                this::callPaymentApi
        );

        try {
            return operation.get();
        } catch (CallNotPermittedException ex) {
            return "Payment service temporarily unavailable";
        }
    }

    /*
        CircuitBreaker + Retry mechanism
    */
    public String processPayment3() {
        Supplier<String> operation =
                () -> callPaymentApi();

        Supplier<String> withRetry =
                Retry.decorateSupplier(paymentRetry, operation);

        Supplier<String> protectedCall =
                CircuitBreaker.decorateSupplier(
                        paymentCircuitBreaker,
                        withRetry
                );

        try {
            return operation.get();
        } catch (CallNotPermittedException ex) {
            return "Payment service temporarily unavailable";
        }

    }


    public String paymentFallback(Throwable ex) {
        return "Payment service is temporarily unavailable";
    }

    private String callPaymentApi() {
        System.out.println("Calling payment API");

        // Simulate a temporary failure
        throw new RuntimeException("Payment API unavailable");
    }
}
