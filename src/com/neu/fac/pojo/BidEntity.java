package com.neu.fac.pojo;

public class BidEntity {
    String id;
    OrderEntity order;
    UserEntity bidder;
    String price;
    String status;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public UserEntity getBidder() {
        return bidder;
    }

    public void setBidder(UserEntity bidder) {
        this.bidder = bidder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
