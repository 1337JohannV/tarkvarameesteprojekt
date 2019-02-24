package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import lombok.Data;

@Data
public class Price {

    private Currency currency;
    private double amount;
}
