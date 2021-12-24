package com.example.Market.Service;


import com.example.Market.Entity.UserEntity;
import com.example.Market.Exception.UserAlreadyExistException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Model.User;
import com.example.Market.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Market.Helper.Helper.getNumeric;
import static com.example.Market.Helper.Helper.isNumeric;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;


    public UserEntity registration(UserEntity userEntity) throws UserAlreadyExistException {
        if (!isNumeric(userEntity.getMoney())) {
            throw new IllegalArgumentException("Значение денег не является цифрой");
        }if (Helper.isNumeric(userEntity.getUsername())){
            throw new IllegalArgumentException("Логин пользователя не может состоять только из цифр ");
        }
        if (userRepo.findByUsername(userEntity.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким логином уже существует");
        }
        userEntity.setMoney(getNumeric(userEntity.getMoney()) + "$");
        return userRepo.save(userEntity);
    }

    public User getUserById(Long id) throws UserNotFoundException {

        if (id == null) {
            UserEntity byUsername = userRepo.findByUsername(Helper.getDefaultGeneratedUserLogin());
            if (byUsername != null) {
                return User.toModel(byUsername);
            } else {
                throw new UserNotFoundException("Пользователь с таким айди не был найден");
            }
        } else {
            UserEntity userEntity = userRepo.findById(id).orElse(null);
            if (userEntity == null) {
                throw new UserNotFoundException("Пользователь с таким айди не был найден");
            }

            return User.toModel(userEntity);
        }
    }

    public Iterable<User> getAllUsers() {
        return User.toModel(userRepo.findAll());
    }

}
