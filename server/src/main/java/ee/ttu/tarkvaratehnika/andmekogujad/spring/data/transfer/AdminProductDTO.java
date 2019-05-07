package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.transfer;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminProductDTO {

    private Long id;

    private String name;

    private String ean;

    private Category category;

    private String imgUrl;
}
