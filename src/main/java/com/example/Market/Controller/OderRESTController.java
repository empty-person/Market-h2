package com.example.Market.Controller;


import com.example.Market.Exception.BasketNotFoundException;
import com.example.Market.Exception.UserNotFoundException;
import com.example.Market.Helper.Helper;
import com.example.Market.Model.Cringe;
import com.example.Market.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OderRESTController {
    @Autowired
    OrderService orderService;


    @PostMapping("/complete")
    public ResponseEntity completeOrder(@RequestBody(required = false) Cringe cringe) {
        try {
            String userName = cringe.getUserName();
            if (userName == null) {
                userName = Helper.getDefaultGeneratedUserLogin();
            }
            return ResponseEntity.ok(orderService.completeOrder(userName));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (BasketNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity getOrderHistory(@RequestBody(required = false) Cringe cringe) {
        try {
            String userName = cringe.getUserName();
            if (userName == null) {
                userName = Helper.getDefaultGeneratedUserLogin();
            }
            return ResponseEntity.ok(orderService.retrieveOrderHistory(userName));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
