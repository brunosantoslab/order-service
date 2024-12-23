package app.brunosantos.orderservice.domain.service;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.model.OrderStatus;
import app.brunosantos.orderservice.infrastructure.producer.OrderProducer;
import app.brunosantos.orderservice.infrastructure.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles order processing logic.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public void processOrder(Order order) {
        BigDecimal totalPrice = order.getProducts().stream()
            .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PROCESSED);

        orderRepository.save(order);

        orderProducer.publishProcessedOrder(order);
    }

    public List<Order> listProcessedOrders() {
        return orderRepository.findByStatus(OrderStatus.PROCESSED);
    }

    public Order findOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @GetMapping
    public List<Order> findOrdersByStatus(@RequestParam OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
}
