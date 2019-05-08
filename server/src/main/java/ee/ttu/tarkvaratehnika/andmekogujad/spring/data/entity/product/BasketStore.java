package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketStore {

    private Store store;
    private double basketPrice;
}
