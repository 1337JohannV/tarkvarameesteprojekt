package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Unit;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class Quantity implements Serializable {

    private Double value;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
