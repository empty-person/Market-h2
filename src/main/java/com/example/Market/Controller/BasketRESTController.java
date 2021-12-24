package com.example.Market.Controller;

import com.example.Market.Exception.BasketNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Model.Cringe;
import com.example.Market.Service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketRESTController {
    @Autowired
    BasketService basketService;

    @GetMapping()
    public ResponseEntity getBasketByUserId() {
        try { //
            System.out.println(Helper.getAuthenticatedUserLogin());
            return ResponseEntity.ok(basketService.getBasketByUserName(Helper.getAuthenticatedUserLogin()));
        } catch (BasketNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @RequestMapping("/add")
    @PostMapping
    public ResponseEntity addItemToBasket(@RequestBody Cringe cringe) {

        String itemName = cringe.getItemName();
        String userName = cringe.getUserName();
        Long quantity = cringe.getQuantity();
        if (userName == null) {
            userName = Helper.getAuthenticatedUserLogin();
        }
        try {
            return ResponseEntity.ok(basketService.addItemToBasket(itemName, quantity, userName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @RequestMapping("/remove")
//    @PostMapping
//    public ResponseEntity removeItemFromBasket(@RequestBody(required = true) String itemName,
//                                               @RequestBody(required = false) Long ownerId,
//                                               @RequestBody(required = false) Long quantity) {
//
//
//        if (ownerId == null) {
//            ownerId = Helper.getQuthUSerId();
//        }
//
//        System.out.println(quantity);
//        System.out.println(")(())(()");
//        System.out.println(itemName);
//        try {
//            return ResponseEntity.ok(basketService.removeItemFromBasket(itemName, quantity, ownerId));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @RequestMapping("/remove")
    @PostMapping
    public ResponseEntity removeItemFromBasket(@RequestBody Cringe cringe) {
        String itemName = cringe.getItemName();
        String userName = cringe.getUserName();
        Long quantity = cringe.getQuantity();

        if (userName == null) {
            userName = Helper.getAuthenticatedUserLogin();
        }


        try {
            return ResponseEntity.ok(basketService.removeItemFromBasket(itemName, quantity, userName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}




