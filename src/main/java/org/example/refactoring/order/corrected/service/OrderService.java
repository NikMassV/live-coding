package org.example.refactoring.order.corrected.service;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.domain.OrderType;
import org.example.refactoring.order.corrected.exception.OrderValidationException;
import org.example.refactoring.order.corrected.repository.OrderRepository;
import org.example.refactoring.order.corrected.validator.order.FlowerOrderValidator;
import org.example.refactoring.order.corrected.validator.order.GiftOrderValidator;
import org.example.refactoring.order.corrected.validator.order.OrderValidatorRegistry;
import org.example.refactoring.order.corrected.validator.order.ToyOrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static long lastId = 0;
    private final OrderRepository orderRepository;
    private final OrderValidatorRegistry orderValidatorRegistry;
    private final DiscountService discountService;

    public OrderService(OrderRepository orderRepository, OrderValidatorRegistry orderValidatorRegistry, DiscountService discountService) {
        this.orderRepository = orderRepository;
        this.orderValidatorRegistry = orderValidatorRegistry;
        this.discountService = discountService;
        orderValidatorRegistry.registerValidator(OrderType.class, new FlowerOrderValidator());
        orderValidatorRegistry.registerValidator(OrderType.class, new GiftOrderValidator());
        orderValidatorRegistry.registerValidator(OrderType.class, new ToyOrderValidator());
    }

    public Mono<Order> createOrder(Order order) {
        long newId = ++lastId;
        order.setId(newId);

        if (order.getType() == null) {
            return Mono.error(new RuntimeException("Order type is required"));
        }

        try {
            Class<?> typeValue = order.getType().getClass();
            orderValidatorRegistry.getValidator(typeValue).validate(order);
        } catch (OrderValidationException e) {
            return Mono.error(e);
        }

        return discountService.applyDiscount(order)
                .flatMap(orderRepository::insertOrder);
    }

    @Transactional
    public Mono<Order> getOrder(Long id) {
        return orderRepository.selectOrder(id);
    }

    @Transactional
    public Mono<Order> markOrderAsCompleted(Long id) {
        return orderRepository.updateOrderAsCompleted(id);
    }
}
