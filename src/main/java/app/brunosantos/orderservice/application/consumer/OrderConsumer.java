package app.brunosantos.orderservice.application.consumer;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes orders from Kafka.
 */
@Component
public class OrderConsumer {

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "orders-in", groupId = "order-service-group")
    public void consumeOrder(Order order) {
        orderService.processOrder(order);
    }
}
