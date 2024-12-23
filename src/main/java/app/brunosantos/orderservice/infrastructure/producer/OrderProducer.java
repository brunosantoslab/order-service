package app.brunosantos.orderservice.infrastructure.producer;

import app.brunosantos.orderservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes processed orders to Kafka.
 */
@Component
public class OrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void publishProcessedOrder(Order order) {
        kafkaTemplate.send("orders-out", order);
    }

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

}
