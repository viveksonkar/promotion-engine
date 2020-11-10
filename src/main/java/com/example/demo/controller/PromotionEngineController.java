package com.example.demo.controller;

import com.example.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionEngineController {

    @Autowired
    CheckoutService checkoutService;

    @GetMapping("/calculate")
    public Integer calculatePromotion(){
        return checkoutService.getFinalPrice(checkoutService.getBasket());
    }

}
