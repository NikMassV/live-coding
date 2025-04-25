package org.example.refactoring.order.source;

public class Order {
    private Long id;
    private String type;
    private String status;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
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

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
}
