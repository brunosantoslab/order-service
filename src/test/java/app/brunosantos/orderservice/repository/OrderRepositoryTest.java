package app.brunosantos.orderservice.repository;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.model.OrderStatus;
import app.brunosantos.orderservice.infrastructure.repository.OrderRepository;
import app.brunosantos.orderservice.util.OrderTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveAndRetrieveOrderByStatus() {
        // Create and save a PENDING order
        Order pendingOrder = new Order();
        pendingOrder.setTotalPrice(new BigDecimal("50.00"));
        pendingOrder.setCreatedAt(LocalDateTime.now());
        pendingOrder.setOrderCode(OrderTestUtils.generateOrderCode(pendingOrder));
        pendingOrder.setStatus(OrderStatus.PENDING);

        orderRepository.save(pendingOrder);

        // Create and save a PROCESSED order
        Order processedOrder = new Order();
        processedOrder.setTotalPrice(new BigDecimal("100.00"));
        processedOrder.setCreatedAt(LocalDateTime.now());
        processedOrder.setOrderCode(OrderTestUtils.generateOrderCode(processedOrder));
        processedOrder.setStatus(OrderStatus.PROCESSED);

        orderRepository.save(processedOrder);

        // Retrieve orders with status PENDING
        List<Order> pendingOrders = orderRepository.findByStatus(OrderStatus.PENDING);
        assertEquals(1, pendingOrders.size());
        assertEquals(OrderStatus.PENDING, pendingOrders.get(0).getStatus());
        assertEquals(new BigDecimal("50.00"), pendingOrders.get(0).getTotalPrice());

        // Retrieve orders with status PROCESSED
        List<Order> processedOrders = orderRepository.findByStatus(OrderStatus.PROCESSED);
        assertEquals(1, processedOrders.size());
        assertEquals(OrderStatus.PROCESSED, processedOrders.get(0).getStatus());
        assertEquals(new BigDecimal("100.00"), processedOrders.get(0).getTotalPrice());
    }

}
