package com.example.Market.Entity;

import com.example.Market.Helper.Helper;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basketId", orphanRemoval=true)
    private List<ItemList> itemList;

    private String totalPrice;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "owner_id")
    private UserEntity ownerId;


    public BasketEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserEntity getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserEntity ownerId) {
        this.ownerId = ownerId;
    }

    public String recalculateBasketTotalPrice() {
        Double doubleResult = 0D;
        for (ItemList list : getItemList()) {
            doubleResult += Helper.getPriceAsNumeric(list.getTotalPrice());
        }
        return doubleResult + "$";
    }

}
