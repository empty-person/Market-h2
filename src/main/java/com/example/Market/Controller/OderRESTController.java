package com.example.Market.Controller;


import com.example.Market.Exception.BasketNotFoundException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OderRESTController {
    @Autowired
    OrderService orderService;


    @PostMapping("/complete")
    public ResponseEntity completeOrder() {
        try {
            return ResponseEntity.ok(orderService.completeOrder(Helper.getAuthenticatedUserLogin()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (BasketNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity getOrderHistory(){
        try {
            return ResponseEntity.ok(orderService.retrieveOrderHistory(Helper.getAuthenticatedUserLogin()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
