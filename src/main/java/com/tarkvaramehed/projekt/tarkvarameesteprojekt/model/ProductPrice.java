package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class ProductPrice implements Serializable {

    @Enumerated(EnumType.STRING)
    private Store store;

    private String url;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "regularPriceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "regularPriceCurrency"))
    })
    private Price regularPrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "specialPriceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "specialPriceCurrency"))
    })
    private Price specialPrice;

    @Embedded
    private UnitPrice unitPrice;

}
