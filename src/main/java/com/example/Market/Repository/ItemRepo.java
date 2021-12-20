package com.example.Market.Repository;

import com.example.Market.Entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<ItemEntity, String> {
    ItemEntity findByName(String name);
}
