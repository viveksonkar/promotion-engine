package com.example.demo.service;

import com.example.demo.engine.PromotionEngine;
import com.example.demo.models.CheckOutItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    PromotionEngine promotionEngine;

    public Integer getFinalPrice(List<CheckOutItem> basket){
        return promotionEngine.applyPromotion(basket);
    }

}
