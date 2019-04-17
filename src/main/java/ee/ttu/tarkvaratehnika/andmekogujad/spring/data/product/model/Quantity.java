package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
public class Quantity implements Serializable {

    private Double value;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
