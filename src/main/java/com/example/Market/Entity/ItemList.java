package com.example.Market.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ItemList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "item_id")
    @ManyToOne
    private ItemEntity itemId;

    private Long quantity;

    private String totalPrice;
    @JsonBackReference
    @JoinColumn(name = "basket_id")
    @ManyToOne
    private BasketEntity basketId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "orderHistory_id")
    private OrderHistoryEntity orderHistory_id;

    public ItemList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemEntity getItemId() {
        return itemId;
    }

    public void setItemId(ItemEntity itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BasketEntity getBasketId() {
        return basketId;
    }

    public void setBasketId(BasketEntity basketId) {
        this.basketId = basketId;
    }

    public OrderHistoryEntity getOrderHistory_id() {
        return orderHistory_id;
    }

    public void setOrderHistory_id(OrderHistoryEntity orderHistory_id) {
        this.orderHistory_id = orderHistory_id;
    }
}

