package basicSyntax.service;

import basicSyntax.entity.OutboxEvent;
import basicSyntax.repository.OrderRepository;
import basicSyntax.repository.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    private final KafkaTemplate<String, String> kafka;
    private final OutboxRepository outboxRepo;

    public OutboxService(
            OutboxRepository outboxRepo,
            KafkaTemplate<String, String> kafka
                ){
        this.kafka = kafka;
        this.outboxRepo = outboxRepo;
    }

    @Scheduled(fixedDelay = 500)
    void relay() throws Exception {
        for (OutboxEvent e :
                outboxRepo.findTop100ByPublishedFalseOrderById()) {

            kafka.send("orders", e.aggregateId(), e.payload()).get();

            e.markPublished();
            outboxRepo.save(e);
        }
    }
}
