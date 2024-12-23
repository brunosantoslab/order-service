package app.brunosantos.orderservice.producer;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.infrastructure.producer.OrderProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

class OrderProducerTest {

    @Mock
    private KafkaTemplate<String, Order> kafkaTemplate;

    @InjectMocks
    private OrderProducer orderProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishOrderToKafka() {
        Order order = new Order();
        order.setId(1L);
        orderProducer.publishProcessedOrder(order);
        verify(kafkaTemplate).send("orders-out", order);
    }
}
