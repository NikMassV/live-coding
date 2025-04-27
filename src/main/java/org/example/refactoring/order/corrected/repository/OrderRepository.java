package org.example.refactoring.order.corrected.repository;

import io.r2dbc.spi.ConnectionFactory;
import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.enums.OrderStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class OrderRepository {

    private final DatabaseClient databaseClient;

    public OrderRepository(ConnectionFactory connectionFactory) {
        this.databaseClient = DatabaseClient.create(connectionFactory);
    }

    public Mono<Order> insertOrder(Order order) {
        String sql = "INSERT INTO orders (id, type, bouquet_name, gift_wrap, set_items, status, price) VALUES ("
                + order.getId() + ", '" + order.getType() + "', "
                + (order.getBouquetName() != null ? ("'" + order.getBouquetName() + "'") : "NULL") + ", "
                + (order.getGiftWrap() != null ? order.getGiftWrap().toString() : "NULL") + ", "
                + (order.getToyName() != null ? ("'" + order.getToyName() + "'") : "NULL") + ", "
                + "'" + order.getStatus() + "', " + order.getPrice() + ")";

        return databaseClient.sql(sql)
                .fetch()
                .rowsUpdated()
                .map(count -> order);
    }

    public Mono<Order> selectOrder(Long id) {
        String sql = "SELECT * FROM orders WHERE id = " + id;
        return databaseClient.sql(sql)
                .map((row, meta) -> {
                    Order o = new Order();
                    o.setId(row.get("id", Long.class));
                    o.setType(row.get("type", String.class));
                    o.setStatus(row.get("status", OrderStatus.class));
                    o.setBouquetName(String.valueOf(row.get("bouquet_name", Enum.class)));
                    o.setToyName(row.get("toy_name", String.class));
                    o.setGiftWrap(row.get("gift_wrap", Boolean.class));
                    o.setPrice(row.get("price", Double.class));
                    return o;
                })
                .one();
    }

    public Mono<Order> updateOrderAsCompleted(Long id) {
        String sql = "UPDATE orders SET status = 'Completed' WHERE id = " + id;
        return databaseClient.sql(sql)
                .fetch()
                .rowsUpdated()
                .flatMap(result -> selectOrder(id));
    }
}
