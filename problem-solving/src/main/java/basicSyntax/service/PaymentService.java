package basicSyntax.service;

import basicSyntax.dto.PaymentRequest;
import basicSyntax.dto.PaymentResponse;
import basicSyntax.entity.IdempotencyRecord;
import basicSyntax.entity.Payment;
import basicSyntax.repository.IdempotencyRepository;
import basicSyntax.repository.PaymentRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class PaymentService {
    private final Retry paymentRetry;
    private final CircuitBreaker paymentCircuitBreaker;

    private final IdempotencyRepository idempotencyRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(
            RetryRegistry retryRegistry,
            CircuitBreakerRegistry circuitBreakerRegistry,
            IdempotencyRepository idempotencyRepository,
            PaymentRepository paymentRepository
                ) {
        this.paymentRetry = retryRegistry.retry("paymentService");
        this.paymentCircuitBreaker = circuitBreakerRegistry.circuitBreaker("payment");

        this.idempotencyRepository = idempotencyRepository;
        this.paymentRepository = paymentRepository;
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

    @Transactional
    public PaymentResponse createPayment(
            String idempotencyKey,
            PaymentRequest request){

        // 1. Check whether this operation was completed already
        var existing = idempotencyRepository
                .findByIdempotencyKey(idempotencyKey);

        if (existing.isPresent()) {
            return existing.get().getResponse();
        }

        // 2. Execute business logic
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());

        // 3. Build response
        PaymentResponse response = new PaymentResponse(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getAmount()
        );

        // 4. Store the key and response
        idempotencyRepository.save(
                new IdempotencyRecord(idempotencyKey, response)
        );

        return response;
    }
}
