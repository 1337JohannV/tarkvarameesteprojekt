package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String ean;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductPrice> productPrices;

    @Embedded
    private Quantity quantity;

    private String producer;

    private String origin;

}