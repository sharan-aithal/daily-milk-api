package io.aithal.dailymilkapi.domain;

public class Order {
    private Long orderId;
    private Integer orderItemId;
    private OrderItem orderItem;
    private Integer totalItem;
    private Double totalPrice;
    private UserProfile userProfile;
    private Long createdAt;
    private Boolean delivered;
    private Delivery delivery;

    public Order ( Long orderId, Integer orderItemId, Integer totalItem, Double totalPrice, Long createdAt, Boolean delivered ) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.totalItem = totalItem;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.delivered = delivered;
    }

    public Order ( Long orderId, Integer orderItemId, OrderItem orderItem, Integer totalItem, Double totalPrice, UserProfile userProfile, Long createdAt, Boolean delivered, Delivery delivery ) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.orderItem = orderItem;
        this.totalItem = totalItem;
        this.totalPrice = totalPrice;
        this.userProfile = userProfile;
        this.createdAt = createdAt;
        this.delivered = delivered;
        this.delivery = delivery;
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

    public OrderItem getOrderItem ( ) {
        return orderItem;
    }

    public void setOrderItem ( OrderItem orderItem ) {
        this.orderItem = orderItem;
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

    public UserProfile getUserProfile ( ) {
        return userProfile;
    }

    public void setUserProfile ( UserProfile userProfile ) {
        this.userProfile = userProfile;
    }

    public Long getCreatedAt ( ) {
        return createdAt;
    }

    public void setCreatedAt ( Long createdAt ) {
        this.createdAt = createdAt;
    }

    public Boolean getDelivered ( ) {
        return delivered;
    }

    public void setDelivered ( Boolean delivered ) {
        this.delivered = delivered;
    }

    public Delivery getDelivery ( ) {
        return delivery;
    }

    public void setDelivery ( Delivery delivery ) {
        this.delivery = delivery;
    }


    public static class Delivery {

        private Long deliveredAt;
        private Boolean status;
        private String failedMessage;

        public Delivery ( Long deliveredAt, Boolean status, String failedMessage ) {
            this.deliveredAt = deliveredAt;
            this.status = status;
            this.failedMessage = failedMessage;
        }

        public Long getDeliveredAt ( ) {
            return deliveredAt;
        }

        public void setDeliveredAt ( Long deliveredAt ) {
            this.deliveredAt = deliveredAt;
        }

        public Boolean getStatus ( ) {
            return status;
        }

        public void setStatus ( Boolean status ) {
            this.status = status;
        }

        public String getFailedMessage ( ) {
            return failedMessage;
        }

        public void setFailedMessage ( String failedMessage ) {
            this.failedMessage = failedMessage;
        }

    }
}
