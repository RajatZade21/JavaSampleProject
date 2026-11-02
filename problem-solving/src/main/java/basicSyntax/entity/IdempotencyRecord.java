package basicSyntax.entity;

import basicSyntax.dto.PaymentResponse;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;

@Entity
public class IdempotencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idempotencyKey;

    @Column(nullable = false)
    private Instant expiresAt;

    PaymentResponse response;

    public IdempotencyRecord(String idempotencyKey, PaymentResponse response) {
        setExpiresAt(Instant.now().plus(Duration.ofHours(48)));
    }

    public void setExpiresAt(Instant plus){
        this.expiresAt = plus;
    }

    public PaymentResponse getResponse() {
        return response;
    }
}
