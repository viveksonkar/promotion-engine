package com.example.demo.service;

import com.example.demo.engine.PromotionEngine;
import com.example.demo.models.CheckOutItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CheckoutService.class })
public class CheckoutServiceTest {

    @Autowired
    CheckoutService checkOutService;

    @MockBean
    PromotionEngine promotionEngine;

    List<CheckOutItem> testData = testData();

    @BeforeEach
    public void setUp(){
        when(promotionEngine.applyPromotion(testData)).thenReturn(350);
    }

    @Test
    public void testGetFinalPrice(){
        assertEquals(350, checkOutService.getFinalPrice(testData));
        Mockito.verify(promotionEngine, times(1)).applyPromotion(testData);
    }

    private List<CheckOutItem> testData(){

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

        return checkOutItems;
    }


}
