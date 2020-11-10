package com.example.demo.controller;

import com.example.demo.models.CheckOutItem;
import com.example.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionEngineController {

    @Autowired
    CheckoutService checkoutService;

    @RequestMapping(path = "/getPrice", method = RequestMethod.POST)
    public ResponseEntity<Integer> getPrice(@RequestBody List<CheckOutItem> cart){
        return ResponseEntity.ok(checkoutService.getFinalPrice(cart));
    }

}
