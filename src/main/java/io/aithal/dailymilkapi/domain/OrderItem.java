package io.aithal.dailymilkapi.domain;

import java.util.List;

public class OrderItem {
    private Integer itemId;
    private Double totalPrice;
    private Integer userId;
    private List<Item> items;

    public OrderItem ( Integer itemId, Double totalPrice, Integer userId, List<Item> items ) {
        this.itemId = itemId;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.items = items;
    }

    public Integer getItemId ( ) {
        return itemId;
    }

    public void setItemId ( Integer itemId ) {
        this.itemId = itemId;
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

    public List<Item> getItems ( ) {
        return items;
    }

    public void setItems ( List<Item> items ) {
        this.items = items;
    }
}
