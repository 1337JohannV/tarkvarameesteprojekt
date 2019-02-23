package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;


import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price {

    private String store;
    private String url;
    private String currency;
    private String specialPrice;
    private double amount;
    private String unitPrice;
}
