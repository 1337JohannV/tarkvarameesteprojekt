package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
@Entity
public class ProductPrice {

    @Enumerated(EnumType.STRING)
    private Store store;

    private String url;

    @OneToOne
    private Price regularPrice;

    @OneToOne
    private Price specialPrice;

    @OneToOne
    private UnitPrice unitPrice;

}
