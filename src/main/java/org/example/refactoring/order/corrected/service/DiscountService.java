package org.example.refactoring.order.corrected.service;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.enums.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountService {

    private final Map<Class<?>, BigDecimal> discounts = new HashMap<>();

    public DiscountService() {
        discounts.put("flower".getClass(), BigDecimal.valueOf(100.0));
        discounts.put("gift".getClass(), BigDecimal.valueOf(50.0));
        discounts.put("set".getClass(), BigDecimal.valueOf(150.0));
    }

    public Mono<Order> applyDiscount(Order order) {
        BigDecimal basePrice = discounts.get(order.getType().getClass());
        if (basePrice == null) {
            throw new IllegalArgumentException("Base price not found for order type: " + order.getType());
        }

        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri("http://discount.example.com/api/discount?type={type}", order.getType())
                .retrieve()
                .bodyToMono(String.class)
                .map(discountStr -> {
                    BigDecimal discount = BigDecimal.ZERO;
                    if (discountStr != null) {
                        try {
                            discount = new BigDecimal(discountStr);
                        } catch (NumberFormatException e) {
                            //add log
                        }
                    }
                    BigDecimal finalPrice = basePrice.multiply(BigDecimal.ONE.subtract(discount));
                    order.setPrice(finalPrice);
                    order.setStatus(OrderStatus.NOT_COMPLETED);
                    return order;
                })
                .onErrorResume(e -> {
                    BigDecimal finalPrice = basePrice;
                    order.setPrice(finalPrice);
                    order.setStatus(OrderStatus.NOT_COMPLETED);
                    return Mono.just(order);
                });
    }
}
