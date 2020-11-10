package com.example.demo.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class PromotionTypeEnumTest {

    @Test
    public void testPromotionTypeEnum(){
        assertTrue(PromotionType.COMBINATION.getPromotionType().equalsIgnoreCase("COMBINATION"));
        assertTrue(PromotionType.QUANTITY.getPromotionType().equalsIgnoreCase("QUANTITY"));
    }
}
