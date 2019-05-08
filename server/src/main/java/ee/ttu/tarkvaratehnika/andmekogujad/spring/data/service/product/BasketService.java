package ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.product;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.BasketStore;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    ProductService productService;


    public List<Product> getProductsListByIDs(List<Integer> ids) {

        List<Product> products = new ArrayList<>();
        for (Integer id : ids) {
            products.add(productService.findById(Long.valueOf(id)));
        }
        return products;
    }

    public BasketStore getShopAndPriceFromList(List<Product> products) {

        double pricePrisma = 0;
        double priceSelver = 0;
        for (Product product : products) {
            System.out.println(product);
            if (product.getProductPrices().size() > 1) {
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
            } else if (product.getProductPrices().size() == 1) {
                if (product.getProductPrices().get(0).getStore().equals(Store.SELVER)
                        || product.getProductPrices().get(0).getStore().equals(Store.PRISMA)) {
                    if (product.getProductPrices().get(0).getSpecialPrice() == null) {
                        priceSelver += product.getProductPrices().get(0).getRegularPrice().getAmount();
                        pricePrisma += product.getProductPrices().get(0).getRegularPrice().getAmount();
                    } else {
                        priceSelver += product.getProductPrices().get(0).getSpecialPrice().getAmount();
                        pricePrisma += product.getProductPrices().get(0).getSpecialPrice().getAmount();

                    }

                }
            }

        }

        if (pricePrisma > priceSelver) {
            return new BasketStore(Store.SELVER, priceSelver);

        } else if (pricePrisma == priceSelver) {
            return new BasketStore(Store.PRISMA, pricePrisma);
        } else {
            return new BasketStore(Store.PRISMA, pricePrisma);
        }
    }

}
