package com.example.demo.engine;

import com.example.demo.data.Promotion;
import com.example.demo.models.CheckOutItem;
import com.example.demo.utils.PromotionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PromotionEngine {

    public Integer applyPromotion(List<CheckOutItem> checkOutItems){

        Map<String, Integer> skuQuantityMap = checkOutItems.stream().collect(Collectors
                .toMap(CheckOutItem::getSku, checkOutItem -> checkOutItem.getQuantity()));

        Map<String, Integer> appliedPromotionMap = new HashMap<>();

        getActivePromotion().forEach( promotion -> {
            // If promotion is on Quantity and Quantity of SKU is greater in checkoutBasket
            if(PromotionType.QUANTITY.getPromotionType().equalsIgnoreCase(promotion.getType()) &&
                    skuQuantityMap.get(promotion.getSkus()) >= promotion.getQuantity() ){
                Integer timesToApply = skuQuantityMap.get(promotion.getSkus())/promotion.getQuantity();
                Integer newQuantity = skuQuantityMap.get(promotion.getSkus()) - timesToApply * promotion.getQuantity();
                skuQuantityMap.put(promotion.getSkus(), newQuantity);
                appliedPromotionMap.put(promotion.getSkus(), promotion.getDiscountedPrice() * timesToApply);
            }
        });

        log.info("PROMOTION =================> "+appliedPromotionMap);
        log.info("QUANTITY_MAP =================> "+skuQuantityMap);
        log.info("Final SUM :=> "+ calculateFinalPrice(skuQuantityMap, appliedPromotionMap));

        return calculateFinalPrice(skuQuantityMap, appliedPromotionMap);

    }

    public Integer calculateFinalPrice( Map<String, Integer> skuQuantityMap, Map<String, Integer>  appliedPromotionMap){

        return skuQuantityMap.entrySet().stream()
                .mapToInt( sku -> sku.getValue() * getSkuPriceList().get(sku.getKey())).sum() +
                appliedPromotionMap.entrySet().stream().mapToInt( sku -> sku.getValue()).sum();

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
