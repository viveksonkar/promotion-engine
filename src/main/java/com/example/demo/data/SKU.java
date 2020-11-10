package com.example.demo.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "SKU")
public class SKU {

    @Id
    @Column(name = "SKU")
    private String sku;

    @Column(name = "PRICE")
    private Integer skuPrice;
}
