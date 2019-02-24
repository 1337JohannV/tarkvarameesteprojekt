package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Unit;
import lombok.Data;

@Data
public class Quantity {

    double value;
    Unit unit;
}
