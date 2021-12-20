package com.example.Market.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemEntity {

    @Id
    private String name;
    private String price;

    public ItemEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
