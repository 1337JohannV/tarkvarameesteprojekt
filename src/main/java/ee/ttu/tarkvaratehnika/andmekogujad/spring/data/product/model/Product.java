package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import lombok.Data;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Indexed
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String ean;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductPrice> productPrices;

    @Embedded
    private Quantity quantity;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String producer;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String origin;

    @Column
    private String imgUrl;

    @Column
    private double basePrice;

    @Column
    private double baseWeight;
}