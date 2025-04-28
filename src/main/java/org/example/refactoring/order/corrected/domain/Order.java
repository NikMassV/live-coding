package org.example.refactoring.order.corrected.domain;

import org.example.refactoring.order.corrected.enums.OrderStatus;

public class Order {

    private Long id;
    private OrderType<String> type;
    private OrderStatus status;
    private String bouquetName;
    private String toyName;
    private Boolean giftWrap;
    private Double price;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType<String> getType() {
        return type;
    }

    public void setType(OrderType<String> type) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
