package com.example.Market.Repository;

import com.example.Market.Entity.ItemList;
import org.springframework.data.repository.CrudRepository;

public interface ItemListRepo extends CrudRepository<ItemList, Long> {
}
