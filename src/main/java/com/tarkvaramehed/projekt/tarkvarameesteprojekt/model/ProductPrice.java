package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import lombok.Data;

@Data
public class ProductPrice {

    private Store store;
    private String url;
    private Price regularPrice;
    private Price specialPrice;
    private UnitPrice unitPrice;

}
