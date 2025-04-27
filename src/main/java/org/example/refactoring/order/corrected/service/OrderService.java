package org.example.refactoring.order.corrected.service;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.enums.OrderStatus;
import org.example.refactoring.order.corrected.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static long lastId = 0;
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Mono<Order> createOrder(Order order) {

        long newId = ++lastId;
        order.setId(newId);

        if (order.getType() == null) {
            return Mono.error(new RuntimeException("Order type is required"));
        }

        switch (order.getType()) {
            case "flower":
                if (order.getBouquetName() == null || order.getBouquetName().isEmpty()) {
                    return Mono.error(new RuntimeException("Bouquet name is required for flower orders"));
                }
                break;
            case "gift":
                if (order.getGiftWrap() == null) {
                    return Mono.error(new RuntimeException("Gift wrap option must be specified for gift orders"));
                }
                break;
            case "toy":
                if (order.getToyName() == null || order.getToyName().isEmpty()) {
                    return Mono.error(new RuntimeException("Set items are required for toy orders"));
                }
                break;
            default:
                return Mono.error(new RuntimeException("Unsupported order type: " + order.getType()));
        }

        double basePrice;
        if ("flower".equals(order.getType())) {
            basePrice = 100.0;
        } else if ("gift".equals(order.getType())) {
            basePrice = 50.0;
        } else if ("set".equals(order.getType())) {
            basePrice = 150.0;
        } else {
            basePrice = 0.0;
        }

        double discount = 0.0;
        try {
            String discountStr = WebClient.create("http://discount.example.com/api/discount?type=" + order.getType())
                    .get()
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (discountStr != null) {
                discount = Double.parseDouble(discountStr);
            }
        } catch (Exception e) {
            discount = 0.0;
        }

        double finalPrice = basePrice;
        if (discount > 0) {
            finalPrice = finalPrice * (1 - discount);
        }
        order.setPrice(finalPrice);

        order.setStatus(OrderStatus.NOT_COMPLETED);

        return orderRepository.insertOrder(order);
    }

    public Mono<Order> getOrder(Long id) {
        return orderRepository.selectOrder(id);
    }

    public Mono<Order> markOrderAsCompleted(Long id) {
        return orderRepository.updateOrderAsCompleted(id);
    }
}

