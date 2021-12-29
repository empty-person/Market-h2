package com.example.Market.Controller;


import com.example.Market.Entity.UserEntity;
import com.example.Market.Exception.UserAlreadyExistException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRESTController {


    @Autowired
    UserService userService;

    /**
     * Retrieves user from database by Query Param 'userId'
     */
    @GetMapping(value = {"", "/{id}"})
    public ResponseEntity getUser(@PathVariable(value = "id", required = false) Object id) {

        if (!Helper.isNumeric(String.valueOf(id))){
            return ResponseEntity.badRequest().body("Необходима цифра в качестве аргумента");
        }
        try {
            // TODO: 12/20/2021 Если запрос прилетает без айди, берем айди залогиненного пользователя

            return ResponseEntity.ok(userService.getUserById(Helper.CastObjectToLong(id)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            return ResponseEntity.badRequest().body("Неправильный аргумент, попробуйте 'id'");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Неправильный аргумент");
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("Регистрация нового пользователя прошла успешна");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage() + "\n " + user.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retrieves all users from database
     */
    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity getAllUser() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
