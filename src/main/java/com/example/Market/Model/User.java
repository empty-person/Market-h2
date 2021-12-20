package com.example.Market.Model;

import com.example.Market.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String username;

    public User() {

    }

    public static User toModel(UserEntity entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        user.setId(entity.getId());
        return user;
    }

    public static Iterable<User> toModel(Iterable<UserEntity> iterable) {
        List<User> userIterable = new ArrayList<>();
        iterable.forEach(item -> {
            userIterable.add(User.toModel(item));
        });
        return userIterable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
