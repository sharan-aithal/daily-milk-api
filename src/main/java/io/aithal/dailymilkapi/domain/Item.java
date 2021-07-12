package io.aithal.dailymilkapi.domain;

public class Item {
    private Long itemNum;
    private Integer productId;
    private Integer quantity;

    public Item ( Long itemNum, Integer productId, Integer quantity ) {
        this.itemNum = itemNum;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getItemNum ( ) {
        return itemNum;
    }

    public void setItemNum ( Long itemNum ) {
        this.itemNum = itemNum;
    }

    public Integer getProductId ( ) {
        return productId;
    }

    public void setProductId ( Integer productId ) {
        this.productId = productId;
    }

    public Integer getQuantity ( ) {
        return quantity;
    }

    public void setQuantity ( Integer quantity ) {
        this.quantity = quantity;
    }
}
