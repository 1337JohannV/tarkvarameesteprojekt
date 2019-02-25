package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class Price implements Serializable {

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
