package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import lombok.*;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.fi.FinnishLightStemFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
@ToString
@Indexed
@AnalyzerDef(name = "nameAnalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = FinnishLightStemFilterFactory.class)
        })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Analyzer(definition = "nameAnalyzer")
    private String name;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String ean;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="product_productprices")
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