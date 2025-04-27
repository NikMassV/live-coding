package org.example.refactoring.order.corrected.controller;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/v1/order")
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/v1/order/{id}")
    public Mono<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/v1/order/{id}/markAsCompleted")
    public Mono<Order> markOrderCompleted(@PathVariable Long id) {
        return orderService.markOrderAsCompleted(id);
    }
}
