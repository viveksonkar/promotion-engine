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

        // 3A + 2A = 130 + 2*50, 2B + 2B + 1B = 45 + 45 + 30 => 350
        assertEquals(Integer.valueOf(350),checkoutService.getFinalPrice(checkOutItems));
    }

    @Test
    public void testScenarioA(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(1);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(1);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);

        assertEquals(Integer.valueOf(100),checkoutService.getFinalPrice(checkOutItems));
    }

    @Test
    public void testScenarioB(){

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

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);

        assertEquals(Integer.valueOf(370),checkoutService.getFinalPrice(checkOutItems));
    }

    @Test
    public void testScenarioC(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(3);

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

        assertEquals(Integer.valueOf(280),checkoutService.getFinalPrice(checkOutItems));
    }
}
