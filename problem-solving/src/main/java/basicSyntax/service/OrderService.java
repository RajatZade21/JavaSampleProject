package basicSyntax.service;

import basicSyntax.dto.PaymentRequest;
import basicSyntax.entity.Order;
import basicSyntax.entity.OutboxEvent;
import basicSyntax.entity.Transaction;
import basicSyntax.repository.OrderRepository;
import basicSyntax.repository.OutboxRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.List;

@Service
public class OrderService {

    private final OutboxRepository outboxRepo;
    private final OrderRepository orderRepo;
    private final RestClient restClient;
    private final WebClient webClient;

    public OrderService(
            OutboxRepository outboxRepo,
            OrderRepository orderRepo,
            RestClient restClient,
            WebClient webClient
                        ){
        this.outboxRepo = outboxRepo;
        this.orderRepo = orderRepo;
        this.restClient = restClient;
        this.webClient = webClient;
    }

    @Transactional
    public void place(Order o) {
        orderRepo.save(o);
        outboxRepo.save(
                OutboxEvent.of("OrderCreated", o.getId(), toJson(o))
        );
    }

    /*
        rate-limiter
        generate RequestNotPermitted if timeoutDuration is zero.
     */
    @Retry(name = "payment")
    @RateLimiter(name = "payment")
    public String callPaymentApi() {
        // Generate once per logical payment operation
        String idempotencyKey = UUID.randomUUID().toString();

        return restClient.get()
                .uri("https://api.example.com/customers")
                .retrieve()
                .body(String.class);
    }

    public Mono<String> createPayment(String orderId,
                                      PaymentRequest request){
        // Generate once per logical payment operation
        String idempotencyKey = UUID.randomUUID().toString();

        return webClient.post()
                .uri("https://payment.example.com/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", idempotencyKey)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<List<Transaction>> fetchTransactions(
            List<Long> accountIds) {

        return Flux.fromIterable(accountIds)
                .flatMap(accountId ->
                        webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                        .scheme("https")
                                        .host("api.example.com")
                                        .path("/transactions")
                                        .queryParam("accountId", accountId)
                                        .build())
                                .retrieve()
                                .bodyToFlux(Transaction.class)
                )
                .collectList();
    }


    private String toJson(Order o) {
        return o.toString();
    }
}
