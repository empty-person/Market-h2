package com.example.Market.Service;

import com.example.Market.Entity.BasketEntity;
import com.example.Market.Entity.ItemList;
import com.example.Market.Entity.OrderHistoryEntity;
import com.example.Market.Entity.UserEntity;
import com.example.Market.Exception.BasketNotFoundException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Repository.OrderHistoryRepo;
import com.example.Market.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderHistoryRepo orderHistoryRepo;

    public List<OrderHistoryEntity> retrieveOrderHistory(String username) throws UserNotFoundException {

        UserEntity userEntity = userRepo.findByUsername(username);
        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь не был найден.");
        }
        return orderHistoryRepo.findAllByUser(userEntity);
    }

    public OrderHistoryEntity completeOrder(String username) throws UserNotFoundException, BasketNotFoundException {

        UserEntity userEntity = userRepo.findByUsername(username);
        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь не был найден.");
        }
        BasketEntity basket = userEntity.getBasket();
        if (basket == null) {
            throw new BasketNotFoundException("Корзина пустая.");
        }

        List<ItemList> itemLists = basket.getItemList();

        if (itemLists.isEmpty()) {
            throw new BasketNotFoundException("Корзина пустая.\"");
        }


        List<ItemList> save = new ArrayList<>();
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setOrderPrice(Helper.getPriceAsNumeric(basket.getTotalPrice()));
        orderHistoryEntity.setTimestamp(new Timestamp(new Date().getTime()));
        orderHistoryEntity.setUser(userEntity);
        for (ItemList itemList : itemLists) {
            ItemList temp = new ItemList();
            temp.setItemId(itemList.getItemId());
            temp.setQuantity(itemList.getQuantity());
            temp.setTotalPrice(itemList.getTotalPrice());
            temp.setOrderHistory_id(orderHistoryEntity);
            save.add(temp);
        }
        orderHistoryEntity.setItemList(save);
        itemLists.removeAll(itemLists);

        orderHistoryRepo.save(orderHistoryEntity);


        return orderHistoryRepo.save(orderHistoryEntity);

    }
}
