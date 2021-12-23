//package com.example.Market.Model;
//
//import com.example.Market.Entity.BasketEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Basket {
//    private Long id;
//
//    private String itemName;
//
//    private Long quantity;
//
//    private String totalPrice;
//
//
//    public static Basket toModel(BasketEntity entity) {
//        Basket basket = new Basket();
//        basket.setId(entity.getId());
//        basket.setQuantity(entity.getQuantity());
//        basket.setItemName(entity.getItemName());
//        basket.setTotalPrice(entity.getTotalPrice());
//        return basket;
//    }
//
//    public static Iterable<Basket> toModel(Iterable<BasketEntity> iterable) {
//        List<Basket> basketIterable = new ArrayList<>();
//        iterable.forEach(item -> {
//            basketIterable.add(Basket.toModel(item));
//        });
//        return basketIterable;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public Long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Long quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(String totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//}
