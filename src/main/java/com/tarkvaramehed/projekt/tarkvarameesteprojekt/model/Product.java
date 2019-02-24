package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String name;
    private String ean;
    private String producer;
    private String origin;
    private List<ProductPrice> prices;
    private Quantity quantity;
}