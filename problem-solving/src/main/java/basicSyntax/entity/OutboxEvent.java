package basicSyntax.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String aggregateId;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private boolean published = false;

    private Instant createdAt = Instant.now();

    protected OutboxEvent() {}

    public static OutboxEvent of(
            String eventType,
            Object aggregateId,
            String payload) {

        OutboxEvent event = new OutboxEvent();
        event.eventType = eventType;
        event.aggregateId = String.valueOf(aggregateId);
        event.payload = payload;
        return event;
    }

    public Long getId() {
        return id;
    }

    public String aggregateId() {
        return aggregateId;
    }

    public String payload() {
        return payload;
    }

    public boolean isPublished() {
        return published;
    }

    public void markPublished() {
        this.published = true;
    }
}