import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.BasketStore;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.BasketService;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Price;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.ProductPrice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class BasketServiceTest {
    private BasketService bs = new BasketService();

    @Test
    void testSpecialPrice(){
        ProductPrice pp1 = new ProductPrice();
        Price price1 = new Price();
        price1.setAmount(25d);
        pp1.setRegularPrice(price1);
        pp1.setStore(Store.PRISMA);

        ProductPrice pp2 = new ProductPrice();
        Price price2 = new Price();
        Price spec = new Price();
        spec.setAmount(5d);
        price2.setAmount(10d);
        pp2.setStore(Store.SELVER);
        pp2.setRegularPrice(price2);
        pp2.setSpecialPrice(spec);

        Product p1 = new Product();
        p1.setProductPrices(Arrays.asList(pp1,pp2));

        BasketStore expected = new BasketStore(Store.SELVER,5d);


        List<Product> products = new ArrayList<>();
        products.add(p1);

        assertEquals(expected,bs.getShopAndPriceFromList(products));
    }

    @Test
    void testTwoProducts(){

        ProductPrice pp1 = new ProductPrice();
        Price price1 = new Price();
        price1.setAmount(25d);
        pp1.setRegularPrice(price1);
        pp1.setStore(Store.PRISMA);

        ProductPrice pp2 = new ProductPrice();
        Price price2 = new Price();
        price2.setAmount(30d);
        pp2.setRegularPrice(price2);
        pp2.setStore(Store.SELVER);

        Product p2 = new Product();

        p2.setProductPrices(Arrays.asList(pp1,pp2));


        ProductPrice pp3 = new ProductPrice();
        Price price3 = new Price();
        price3.setAmount(65d);
        pp3.setRegularPrice(price3);
        pp3.setStore(Store.PRISMA);

        ProductPrice pp4 = new ProductPrice();
        Price price4 = new Price();
        price4.setAmount(65.5d);
        pp4.setRegularPrice(price4);
        pp4.setStore(Store.SELVER);

        Product p3 = new Product();

        p3.setProductPrices(Arrays.asList(pp3,pp4));

        BasketStore expected = new BasketStore(Store.PRISMA,90d);


        assertEquals(expected,bs.getShopAndPriceFromList(Arrays.asList(p3,p2)));

    }
    @Test
    void testMoreProducts() {
        ProductPrice pp1 = new ProductPrice();
        Price price1 = new Price();
        price1.setAmount(50.5d);
        pp1.setRegularPrice(price1);
        pp1.setStore(Store.PRISMA);

        ProductPrice pp2 = new ProductPrice();
        Price price2 = new Price();
        price2.setAmount(25.5d);
        pp2.setRegularPrice(price2);
        pp2.setStore(Store.SELVER);

        Product p2 = new Product();

        p2.setProductPrices(Arrays.asList(pp1,pp2));


        ProductPrice pp3 = new ProductPrice();
        Price price3 = new Price();
        price3.setAmount(43.3d);
        pp3.setRegularPrice(price3);
        pp3.setStore(Store.PRISMA);

        ProductPrice pp4 = new ProductPrice();
        Price price4 = new Price();
        Price priceSpecial = new Price();
        priceSpecial.setAmount(5d);
        price4.setAmount(65.5d);
        pp4.setSpecialPrice(priceSpecial);
        pp4.setRegularPrice(price4);
        pp4.setStore(Store.SELVER);

        Product p3 = new Product();

        p3.setProductPrices(Arrays.asList(pp3,pp4));

        ProductPrice pp5 = new ProductPrice();
        Price price5 = new Price();
        price5.setAmount(30d);
        pp5.setRegularPrice(price5);
        pp5.setStore(Store.SELVER);

        ProductPrice pp6 = new ProductPrice();
        Price price6 = new Price();
        price6.setAmount(25d);
        pp6.setRegularPrice(price6);
        pp6.setStore(Store.PRISMA);

        Product p4 = new Product();

        p4.setProductPrices(Arrays.asList(pp5,pp6));



        BasketStore expected = new BasketStore(Store.SELVER,60.5d);


        assertEquals(expected,bs.getShopAndPriceFromList(Arrays.asList(p3,p2,p4)));
    }
}
