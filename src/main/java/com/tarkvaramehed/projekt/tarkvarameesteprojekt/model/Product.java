package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String ean;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductPrice> prices;

    @OneToOne
    private Quantity quantity;

    private String producer;

    private String origin;

}