package basicSyntax.service;

import basicSyntax.entity.Order;
import basicSyntax.entity.OutboxEvent;
import basicSyntax.repository.OrderRepository;
import basicSyntax.repository.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OutboxRepository outboxRepo;
    private final OrderRepository orderRepo;


    public OrderService(
            OutboxRepository outboxRepo,
            OrderRepository orderRepo
                        ){
        this.outboxRepo = outboxRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public void place(Order o) {
        orderRepo.save(o);
        outboxRepo.save(
                OutboxEvent.of("OrderCreated", o.getId(), toJson(o))
        );
    }



    private String toJson(Order o) {
        return o.toString();
    }
}
