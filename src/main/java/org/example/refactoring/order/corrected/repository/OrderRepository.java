package org.example.refactoring.order.corrected.repository;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.domain.OrderType;
import org.example.refactoring.order.corrected.enums.OrderStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final DatabaseClient databaseClient;

    public OrderRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Mono<Order> insertOrder(Order order) {
        return databaseClient.sql("""
                        INSERT INTO orders
                        (id, type, boquet_name, gift_wrap, toy_name, status, price)
                        VALUES
                        (:id, :type, :boquetName, :giftWrap, :toyName, :status, :price)
                        """)
                .bind("id", order.getId())
                .bind("type", order.getType().getType().toString())
                .bind("bouquetName", order.getBouquetName())
                .bind("giftWrap", order.getGiftWrap())
                .bind("toyName", order.getToyName())
                .bind("status", order.getStatus().toString())
                .bind("price", order.getPrice())
                .fetch()
                .rowsUpdated()
                .map(row -> {
                    order.setId(UUID.randomUUID());
                    return order;
                })
                .thenReturn(order);
    }

    public Mono<Order> selectOrder(Long id) {
        return databaseClient.sql("SELECT * FROM orders WHERE id = :id")
                .bind("id", id)
                .map((row, meta) -> {
                    Order o = new Order();
                    o.setId(row.get("id", UUID.class));
                    OrderType<?> rawType = row.get("type", OrderType.class);
                    OrderType<String> typedType = new OrderType<>();
                    typedType.setType(Objects.requireNonNull(rawType).getType().toString());
                    o.setType(typedType);
                    o.setStatus(row.get("status", OrderStatus.class));
                    o.setBouquetName(String.valueOf(row.get("bouquet_name", Enum.class)));
                    o.setToyName(row.get("toy_name", String.class));
                    o.setGiftWrap(row.get("gift_wrap", Boolean.class));
                    o.setPrice(row.get("price", BigDecimal.class));
                    return o;
                })
                .one();
    }

    public Mono<Order> updateOrderAsCompleted(Long id) {
        return databaseClient.sql("UPDATE orders SET status = 'COMPLETED' WHERE id = :id")
                .bind("id", id)
                .fetch()
                .rowsUpdated()
                .flatMap(updatedCount -> updatedCount > 0
                        ? selectOrder(id)
                        : Mono.error(new NoSuchElementException("Order not found"))
                );
    }
}
