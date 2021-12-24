package com.example.Market.Service;


import com.example.Market.Entity.ItemEntity;
import com.example.Market.Entity.UserEntity;
import com.example.Market.Exception.ItemAlreadyExistException;
import com.example.Market.Exception.ItemNotFoundException;
import com.example.Market.Exception.UserAlreadyExistException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Model.User;
import com.example.Market.Repository.ItemRepo;
import com.example.Market.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Market.Helper.Helper.isNumeric;
import static com.example.Market.Helper.Helper.isStringContainsNumbers;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemRepo;


    public ItemEntity createItem(ItemEntity itemEntity) throws ItemAlreadyExistException {
        if (!isNumeric(itemEntity.getPrice())){
            throw new IllegalArgumentException("Не является цифрой");
        }if (isStringContainsNumbers(itemEntity.getName())){
            throw new IllegalArgumentException("Название не может содержать цифру");
        }
        if (itemRepo.findByName(itemEntity.getName()) != null) {
            throw new ItemAlreadyExistException("Предмет с таким названием уже существует");
        }
        itemEntity.setPrice(itemEntity.getPrice()+"$");
        return itemRepo.save(itemEntity);

    }

    public ItemEntity getItemById(String id) throws ItemNotFoundException {
        ItemEntity itemEntity = itemRepo.findById(id).orElse(null);
        if (itemEntity == null) {
            throw new ItemNotFoundException("Предмет с таким названием не был найден");
        }

        return itemEntity;
    }

    public Iterable<ItemEntity> getAllItems() {
        return itemRepo.findAll();
    }

}
