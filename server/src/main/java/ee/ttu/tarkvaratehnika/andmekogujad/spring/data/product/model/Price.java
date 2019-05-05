package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
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
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
