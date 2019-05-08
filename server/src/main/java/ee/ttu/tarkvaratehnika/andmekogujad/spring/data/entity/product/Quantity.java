package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Quantity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double value;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
