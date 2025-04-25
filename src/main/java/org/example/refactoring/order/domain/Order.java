package org.example.refactoring.order.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.example.refactoring.order.enums.OrderStatus;
import org.example.refactoring.order.enums.OrderType;

import java.math.BigDecimal;

public class Order {

    private Long id;
    private OrderType type;
    private OrderStatus status;
    private String bouquetName;
    private String toyName;
    private Boolean giftWrap;
    private BigDecimal price;

    public Order() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
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
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
