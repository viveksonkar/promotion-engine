package com.example.demo.engine;

import com.example.demo.data.SKU;
import com.example.demo.models.CheckOutItem;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.repository.SKURepository;
import com.example.demo.utils.PromotionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PromotionEngine {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    SKURepository skuRepository;

    public Integer applyPromotion(List<CheckOutItem> checkOutItems){

        Map<String, Integer>  skuPriceList = StreamSupport.stream(skuRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(SKU::getSku, sku -> sku.getSkuPrice()));

        Map<String, Integer> skuQuantityMap = checkOutItems.stream().collect(Collectors
                .toMap(CheckOutItem::getSku, checkOutItem -> checkOutItem.getQuantity()));

        Map<String, Integer> appliedPromotionMap = new HashMap<>();

        Optional.ofNullable(promotionRepository.findAll()).ifPresent( promotions -> {
            promotions.forEach( promotion -> {
                // If promotion is on Quantity and Quantity of SKU is greater in checkoutBasket
                if(PromotionType.QUANTITY.getPromotionType().equalsIgnoreCase(promotion.getType()) &&
                        skuQuantityMap.get(promotion.getSkus()) >= promotion.getQuantity() ){
                    Integer timesToApply = skuQuantityMap.get(promotion.getSkus())/promotion.getQuantity();
                    Integer newQuantity = skuQuantityMap.get(promotion.getSkus()) - timesToApply * promotion.getQuantity();
                    skuQuantityMap.put(promotion.getSkus(), newQuantity);
                    appliedPromotionMap.put(promotion.getSkus(), promotion.getDiscountedPrice() * timesToApply);
                }
                // If promotion is Combination then we have to check it required quantities are present
                else if(PromotionType.COMBINATION.getPromotionType().equalsIgnoreCase(promotion.getType())){

                    List<String> skuList = Arrays.asList(promotion.getSkus().split(",")).stream()
                            .filter( sku -> null != skuQuantityMap.get(sku) && skuQuantityMap.get(sku) >= promotion.getQuantity())
                            .collect(Collectors.toList());

                    if(!CollectionUtils.isEmpty(skuList) && skuList.size() == promotion.getSkus().split(",").length){
                        Integer timesToApply = skuList.stream().map(skuQuantityMap::get)
                                .mapToInt( quantity -> quantity).min().orElse(0);
                        skuList.forEach(sku -> {
                            Integer newQuantity = skuQuantityMap.get(sku) - timesToApply * promotion.getQuantity();
                            skuQuantityMap.put(sku, newQuantity);
                        });
                        appliedPromotionMap.put(promotion.getSkus(), promotion.getDiscountedPrice() * timesToApply);
                    }
                }
            });
        });

        return calculateFinalPrice(skuQuantityMap, appliedPromotionMap, skuPriceList);

    }

    public Integer calculateFinalPrice( Map<String, Integer> skuQuantityMap,
                                        Map<String, Integer>  appliedPromotionMap,
                                        Map<String, Integer>  skuPriceList){

        return skuQuantityMap.entrySet().stream()
                .mapToInt( sku -> sku.getValue() * skuPriceList.get(sku.getKey())).sum() +
                appliedPromotionMap.entrySet().stream().mapToInt( sku -> sku.getValue()).sum();

    }


}
