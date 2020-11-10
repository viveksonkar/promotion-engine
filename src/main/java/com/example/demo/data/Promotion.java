package com.example.demo.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {
    private String type;
    private Integer quantity;
    private String skus;
    private Integer discountedPrice;
}
