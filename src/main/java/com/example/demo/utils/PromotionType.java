package com.example.demo.utils;

import lombok.Data;
import lombok.Getter;

@Getter
public enum PromotionType {

    QUANTITY("QUANTITY"),
    COMBINATION("COMBINATION"),;

    private final String promotionType;

    PromotionType(String promotionType) {
        this.promotionType = promotionType;
    }
}
