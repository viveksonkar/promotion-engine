package com.example.demo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "PROMOTION")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "SKUS")
    private String skus;

    @Column(name = "DISCOUNTED_PRICE")
    private Integer discountedPrice;
}
