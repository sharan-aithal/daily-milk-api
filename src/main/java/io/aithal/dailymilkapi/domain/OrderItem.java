package io.aithal.dailymilkapi.domain;

public class OrderItem {
    private Integer itemId;
    private Long itemNum;
    private Double totalPrice;
    private Integer userId;

    public OrderItem ( Integer itemId, Long itemNum, Double totalPrice, Integer userId ) {
        this.itemId = itemId;
        this.itemNum = itemNum;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public Integer getItemId ( ) {
        return itemId;
    }

    public void setItemId ( Integer itemId ) {
        this.itemId = itemId;
    }

    public Long getItemNum ( ) {
        return itemNum;
    }

    public void setItemNum ( Long itemNum ) {
        this.itemNum = itemNum;
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
}
