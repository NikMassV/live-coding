package org.example.refactoring.order.corrected.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.refactoring.order.corrected.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {

    private UUID id;
    private OrderType<?> type;
    private OrderStatus status;
    private String bouquetName;
    private String toyName;
    private Boolean giftWrap;
    private BigDecimal price;

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderType<?> getType() {
        return type;
    }

    public void setType(OrderType<?> type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getBouquetName() {
        return bouquetName;
    }

    public void setBouquetName(String bouquetName) {
        this.bouquetName = bouquetName;
    }

    public Boolean getGiftWrap() {
        return giftWrap;
    }

    public void setGiftWrap(Boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(id, order.id)
                .append(type, order.type)
                .append(status, order.status)
                .append(bouquetName, order.bouquetName)
                .append(toyName, order.toyName)
                .append(giftWrap, order.giftWrap)
                .append(price, order.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .append(status)
                .append(bouquetName)
                .append(toyName)
                .append(giftWrap)
                .append(price)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", bouquetName='" + bouquetName + '\'' +
                ", toyName='" + toyName + '\'' +
                ", giftWrap=" + giftWrap +
                ", price=" + price +
                '}';
    }
}
