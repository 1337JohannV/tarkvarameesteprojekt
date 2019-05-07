package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.ProductPrice;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;

@Service
public class BasketService {

    public HashMap<Store, Double> getShopAndPriceFromList(List<Product> products) {

        HashMap<Store, Double> storeMap = new HashMap<>();
        double pricePrisma = 0;
        double priceSelver = 0;
        for (Product product : products) {

            for (ProductPrice productPrice : product.getProductPrices()) {

                if (productPrice.getSpecialPrice() == null) {
                    if (productPrice.getStore().equals(Store.PRISMA)) {
                        pricePrisma += productPrice.getRegularPrice().getAmount();
                    }
                    if (productPrice.getStore().equals(Store.SELVER)) {
                        priceSelver += productPrice.getRegularPrice().getAmount();
                    }

                } else if (productPrice.getStore().equals(Store.SELVER) && productPrice.getSpecialPrice() != null) {
                    priceSelver += productPrice.getSpecialPrice().getAmount();
                }

            }
        }

        if (pricePrisma > priceSelver) {
            storeMap.put(Store.SELVER, priceSelver);
            return storeMap;

        } else if (pricePrisma == priceSelver) {
            return null;
        } else {
            storeMap.put(Store.PRISMA, pricePrisma);
            return storeMap;
        }
    }

}
