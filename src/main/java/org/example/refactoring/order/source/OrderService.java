package org.example.refactoring.order.source;

import io.r2dbc.spi.ConnectionFactory;
import org.example.refactoring.order.enums.OrderType;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static long lastId = 0;
    private final DatabaseClient databaseClient;

    public OrderService(ConnectionFactory connectionFactory) {
        this.databaseClient = DatabaseClient.create(connectionFactory);
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

        order.setStatus("Not completed");

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

    public Mono<Order> getOrder(Long id) {
        String sql = "SELECT * FROM orders WHERE id = " + id;
        return databaseClient.sql(sql)
                .map((row, meta) -> {
                    Order o = new Order();
                    o.setId(row.get("id", Long.class));
                    o.setType(String.valueOf(row.get("type", Enum.class)));
                    o.setBouquetName(String.valueOf(row.get("bouquet_name", Enum.class)));
                    o.setToyName(row.get("toy_name", String.class));
                    o.setGiftWrap(row.get("gift_wrap", Boolean.class));
                    o.setStatus(row.get("status", String.class));
                    o.setPrice(row.get("price", Double.class));
                    return o;
                })
                .one();
    }

    public Mono<Order> markOrderAsCompleted(Long id) {
        String sql = "UPDATE orders SET status = 'Completed' WHERE id = " + id;
        return databaseClient.sql(sql)
                .fetch()
                .rowsUpdated()
                .flatMap(result -> getOrder(id));
    }
}
