package com.example.Market.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String money;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private BasketEntity basket;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderHistoryEntity> orderHistoryEntities;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public BasketEntity getBasket() {
        return basket;
    }

    public void setBasket(BasketEntity basket) {
        this.basket = basket;
    }

    public List<OrderHistoryEntity> getOrderHistoryEntities() {
        return orderHistoryEntities;
    }

    public void setOrderHistoryEntities(List<OrderHistoryEntity> orderHistoryEntities) {
        this.orderHistoryEntities = orderHistoryEntities;
    }
//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
