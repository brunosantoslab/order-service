package app.brunosantos.orderservice.infrastructure.repository;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.model.OrderStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing orders in PostgreSQL.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}
