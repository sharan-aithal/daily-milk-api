package io.aithal.dailymilkapi.domain;

public class Order {
    private Long orderId;
    private Integer orderItemId;
    private Long createdAt;
    private Integer totalItem;
    private Double totalPrice;
    private Integer userId;
    private Boolean delivered;

    public Order ( Long orderId, Integer orderItemId, Long createdAt, Integer totalItem, Double totalPrice, Integer userId, Boolean delivered ) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.createdAt = createdAt;
        this.totalItem = totalItem;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.delivered = delivered;
    }

    public Long getOrderId ( ) {
        return orderId;
    }

    public void setOrderId ( Long orderId ) {
        this.orderId = orderId;
    }

    public Integer getOrderItemId ( ) {
        return orderItemId;
    }

    public void setOrderItemId ( Integer orderItemId ) {
        this.orderItemId = orderItemId;
    }

    public Integer getTotalItem ( ) {
        return totalItem;
    }

    public void setTotalItem ( Integer totalItem ) {
        this.totalItem = totalItem;
    }

    public Double getTotalPrice ( ) {
        return totalPrice;
    }

    public void setTotalPrice ( Double totalPrice ) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId ( ) {
        return userId;
    }

    public void setUserId ( Integer userId ) {
        this.userId = userId;
    }

    public Boolean getDelivered ( ) {
        return delivered;
    }

    public void setDelivered ( Boolean delivered ) {
        this.delivered = delivered;
    }

    public Long getCreatedAt ( ) {
        return createdAt;
    }

    public void setCreatedAt ( Long createdAt ) {
        this.createdAt = createdAt;
    }


    public static class Delivery {

        private String deliveredAt;
        private String status;

        public Delivery ( ) {
        }
    }
}
