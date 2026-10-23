package basicSyntax.service;

import basicSyntax.dto.records.OrderCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreated event) {
        System.out.println(
                "Processing payment for order: " + event.orderId()
        );
    }
}
