package basicSyntax.service;

import basicSyntax.repository.IdempotencyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class IdempotencyCleanupJob {
    private final IdempotencyRepository repository;

    public IdempotencyCleanupJob(IdempotencyRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void purgeExpiredRecords() {
        repository.deleteExpired(Instant.now());
    }
}
