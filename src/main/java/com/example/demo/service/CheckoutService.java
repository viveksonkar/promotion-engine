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

    public List<CheckOutItem> getBasket(){
        List<CheckOutItem> checkOutItems = new ArrayList<>();

        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(5);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        CheckOutItem checkOutItemD = new CheckOutItem();
        checkOutItemD.setSku("D");
        checkOutItemD.setQuantity(1);
        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);
        checkOutItems.add(checkOutItemD);

        return checkOutItems;
    }

}
