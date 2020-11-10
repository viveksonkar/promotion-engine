package com.example.demo.engine;

import com.example.demo.data.Promotion;
import com.example.demo.models.CheckOutItem;
import com.example.demo.utils.PromotionType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PromotionEngine {

    public Integer applyPromotion(List<CheckOutItem> checkOutItems){

        return 0;

    }

    private Map<String, Integer> getSkuPriceList(){
        Map<String, Integer> skuPriceMap = new HashMap<>();
        skuPriceMap.put("A", 50);
        skuPriceMap.put("B", 30);
        skuPriceMap.put("C", 20);
        skuPriceMap.put("D", 15);
        return skuPriceMap;
    }

    public List<Promotion> getActivePromotion(){
        List<Promotion> activePromotions = new ArrayList<>();
        Promotion promotion = new Promotion();
        promotion.setType(PromotionType.QUANTITY.getPromotionType());
        promotion.setSkus("A");
        promotion.setQuantity(3);
        promotion.setDiscountedPrice(130);

        Promotion promotion2 = new Promotion();
        promotion2.setType(PromotionType.QUANTITY.getPromotionType());
        promotion2.setSkus("B");
        promotion2.setQuantity(2);
        promotion2.setDiscountedPrice(45);

        Promotion promotion3 = new Promotion();
        promotion3.setType(PromotionType.COMBINATION.getPromotionType());
        promotion3.setSkus("C,D");
        promotion3.setQuantity(1);
        promotion3.setDiscountedPrice(30);

        activePromotions.add(promotion);
        activePromotions.add(promotion2);
        activePromotions.add(promotion3);

        return activePromotions;
    }

}
