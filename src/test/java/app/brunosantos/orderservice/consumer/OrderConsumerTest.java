package app.brunosantos.orderservice.consumer;

import app.brunosantos.orderservice.application.consumer.OrderConsumer;
import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class OrderConsumerTest {

    @Mock
    private OrderService orderProcessingService;

    @InjectMocks
    private OrderConsumer orderConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConsumeOrderAndProcess() {
        Order order = new Order();
        orderConsumer.consumeOrder(order);
        verify(orderProcessingService).processOrder(order);
    }
}
