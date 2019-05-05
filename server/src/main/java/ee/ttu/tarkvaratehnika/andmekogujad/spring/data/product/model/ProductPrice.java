package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class ProductPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private Store store;

    private String url;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "regularPriceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "regularPriceCurrency"))})
    private Price regularPrice;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "specialPriceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "specialPriceCurrency"))})
    private Price specialPrice;

    @Embedded
    private UnitPrice unitPrice;

}
