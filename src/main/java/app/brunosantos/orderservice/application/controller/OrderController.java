package app.brunosantos.orderservice.application.controller;

import app.brunosantos.orderservice.domain.model.Order;
import app.brunosantos.orderservice.domain.model.OrderStatus;
import app.brunosantos.orderservice.domain.service.OrderService;
import app.brunosantos.orderservice.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller to expose order data for external systems.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint to get all processed orders.
     */
    @GetMapping("/processed")
    public List<Order> listProcessedOrders() {
        return orderService.listProcessedOrders();
    }

    /**
     * Endpoint to get an order by its ID.
     */
    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    /**
     * Endpoint to query orders by status.
     */
    @GetMapping
    public List<Order> findOrderByStatus(@RequestParam OrderStatus status) {
        return orderService.findOrdersByStatus(status);
    }
}
