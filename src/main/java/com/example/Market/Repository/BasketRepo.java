package com.example.Market.Repository;

import com.example.Market.Entity.BasketEntity;
import com.example.Market.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketRepo extends CrudRepository<BasketEntity, Long> {
     BasketEntity findByOwnerId(UserEntity owner_id);
}
