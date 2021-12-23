package com.example.Market.Repository;

import com.example.Market.Entity.OrderHistoryEntity;
import com.example.Market.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderHistoryRepo extends CrudRepository<OrderHistoryEntity, Long> {

    List<OrderHistoryEntity> findAllByUser(UserEntity user);
}
