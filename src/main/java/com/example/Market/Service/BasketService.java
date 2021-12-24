package com.example.Market.Service;

import com.example.Market.Entity.BasketEntity;
import com.example.Market.Entity.ItemEntity;
import com.example.Market.Entity.ItemList;
import com.example.Market.Entity.UserEntity;
import com.example.Market.Exception.BasketNotFoundException;
import com.example.Market.Exception.ItemNotFoundException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Model.User;
import com.example.Market.Repository.BasketRepo;
import com.example.Market.Repository.ItemListRepo;
import com.example.Market.Repository.ItemRepo;
import com.example.Market.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    ItemListRepo itemListRepo;

    boolean go;


    public BasketEntity removeItemFromBasket(String itemName, Long quantity, String userName) throws BasketNotFoundException, ItemNotFoundException {
        if (quantity == null) {
            quantity = 1L;
        }
        BasketEntity basketByOwnerId = basketRepo.findByOwnerId(userRepo.findByUsername(userName));
        if (basketByOwnerId == null) {
            throw new BasketNotFoundException("Корзина пользователя пустая");
        }
        List<ItemList> itemLists = basketByOwnerId.getItemList();
        boolean itemExistInItemList = false;

        for (ItemList itemList : itemLists) {
            if (itemList.getItemId().getName().equals(itemName)) {

                itemExistInItemList = true;

                if (itemList.getQuantity() - quantity < 1) {
                    itemList.setQuantity(itemList.getQuantity() - quantity);
                    itemLists.remove(itemList);
                    itemListRepo.deleteById(itemList.getId());
                } else {
                    itemList.setTotalPrice(Helper.getPriceAsNumeric(itemList.getTotalPrice())
                            - Helper.getPriceAsNumeric(itemList.getItemId().getPrice())
                            + "$");
                    itemList.setQuantity(itemList.getQuantity() - quantity);
                }
                break;
            }
        }
        if (itemExistInItemList) {
            basketByOwnerId.setTotalPrice(basketByOwnerId.recalculateBasketTotalPrice());
            return basketRepo.save(basketByOwnerId);
        } else throw new ItemNotFoundException("Предмет не был найден в корзине");

    }

    public BasketEntity addItemToBasket(String itemName, Long quantity, String userName) throws ItemNotFoundException, UserNotFoundException {

        if (quantity == null) {
            quantity = 1L;
        }

        ItemEntity item = itemRepo.findByName(itemName);
        if (item == null) {
            throw new ItemNotFoundException("Предмет не существует");
        }

        UserEntity userEntity = userRepo.findByUsername(userName);

        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь не существует");
        }
        BasketEntity basketById = basketRepo.findByOwnerId(userEntity);

        if (basketById != null) {
            try {
                return basketRepo.save(updateBasket(basketById, item, quantity));
            } catch (NullPointerException e) {
                return basketById;
            }
            //updateBasket(basketRepo.findByItemNameAndOwnerId(itemName, userId).getId(), basket.getQuantity());
        }


        BasketEntity basket = new BasketEntity();

        ItemList itemList = new ItemList();
        System.out.println("_________________");
        System.out.println(userName);
        System.out.println(userEntity);
        System.out.println("_________________");
        basket.setOwnerId(userEntity);


        basket.setTotalPrice(item.getPrice());

        BasketEntity save = basketRepo.save(basket);
        itemList.setItemId(item);
        itemList.setBasketId(basket);
        itemList.setQuantity(quantity);
        itemList.setTotalPrice(item.getPrice());
        List<ItemList> itemListList = new ArrayList<>();
        itemListList.add(itemList);
        save.setItemList(itemListList);

        System.out.println(basket.getTotalPrice());
        System.out.println(save.getTotalPrice());
        return basketRepo.save(save);
    }

    public BasketEntity updateBasket(BasketEntity basket, ItemEntity item, Long quantity) throws NullPointerException {
        go = true;


        for (ItemList list : basket.getItemList()) {

            if (list.getItemId().getName().equals(item.getName())) {
                list.setQuantity(list.getQuantity() + quantity);
                list.setTotalPrice(
                        Helper.getPriceAsNumeric(list.getTotalPrice())
                                + (Helper.getPriceAsNumeric(item.getPrice()) * quantity)
                                + "$");
                go = false;
                basket.setTotalPrice(basket.recalculateBasketTotalPrice());

                return basket;
            }
            //itemList = itemListRepo.save(itemList);
        }
        if (go) {
            ItemList itemList = new ItemList();

            itemList.setBasketId(basket);
            itemList.setItemId(item);
            itemList.setQuantity(quantity);
            itemList.setTotalPrice((Helper.getPriceAsNumeric(itemList.getItemId().getPrice()) * quantity) + "$");
            basket.getItemList().add(itemList);
            itemListRepo.save(itemList);
        }


        basket.setTotalPrice(basket.recalculateBasketTotalPrice());

        return basket;
    }

    public BasketEntity getBasketByUserName(String userName) throws BasketNotFoundException {

        return basketRepo.findByOwnerId(userRepo.findByUsername(userName));


    }

    public Iterable<User> getAllUsers() {
        return User.toModel(userRepo.findAll());
    }
}
