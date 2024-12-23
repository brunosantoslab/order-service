package app.brunosantos.orderservice.service;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.model.OrderStatus;
import app.brunosantos.orderservice.domain.model.Product;
import app.brunosantos.orderservice.domain.service.OrderService;
import app.brunosantos.orderservice.infrastructure.producer.OrderProducer;
import app.brunosantos.orderservice.infrastructure.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProducer orderProducer;

    @InjectMocks
    private OrderService orderProcessingService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order();
        order.setProducts(List.of(
                new Product("Product A", 2, new BigDecimal("10.00")),
                new Product("Product B", 1, new BigDecimal("20.00"))
        ));
    }

    @Test
    void shouldProcessOrderCorrectly() {
        // Act
        orderProcessingService.processOrder(order);

        // Assert
        verify(orderRepository, times(1)).save(order);
        verify(orderProducer, times(1)).publishProcessedOrder(order);
        assertEquals(new BigDecimal("40.00"), order.getTotalPrice());
        assertEquals(OrderStatus.PROCESSED, order.getStatus());
    }
}
