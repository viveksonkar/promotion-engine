package com.example.demo.service;

import com.example.demo.engine.PromotionEngine;
import com.example.demo.models.CheckOutItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CheckoutService.class, PromotionEngine.class })
public class CheckoutServiceTest {

    @Autowired
    CheckoutService checkoutService;

    @Test
    public void testFinalPrice(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(5);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);

        assertEquals(Integer.valueOf(350),checkoutService.getFinalPrice(checkOutItems));
    }
}
