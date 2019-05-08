import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.PrismaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrismaScraperTest {

    private PrismaScraper prismaScraper = new PrismaScraper();

    @Test
    void testName(){
        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");
            //check name
            String expected = "Coca-Cola Zero Peach karastusjook 1,5L";
            String expected1 = "Doktorivorst 300 g";

            assertEquals(expected,p.getName());
            assertEquals(expected1,p1.getName());

        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testEan() {

        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");
            //check ean
            String expected = "5449000272805";
            String expected1 = "4740003003269";

            assertEquals(expected,p.getEan());
            assertEquals(expected1,p1.getEan());

        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testProducer(){


        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");
            //check producer
            String expected = "COCA COLA";
            String expected1 = "RAKVERE";

            assertEquals(expected,p.getProducer());
            assertEquals(expected1,p1.getProducer());



        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testOrigin() {
        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");
            //check producer
            String expected = "Poola";
            String expected1 = "Eesti";

            assertEquals(expected,p.getOrigin());
            assertEquals(expected1,p1.getOrigin());



        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testProductPrices() {

        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");

            ProductPrice expected = new ProductPrice();
            expected.setStore(Store.PRISMA);
            expected.setUnitPrice(new UnitPrice(0.9d,Currency.EUR ,Unit.L));
            expected.setUrl("https://prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            expected.setRegularPrice(new Price(1.35d, Currency.EUR));

            ProductPrice expected1 = new ProductPrice();
            expected1.setStore(Store.PRISMA);
            expected1.setUrl("https://prismamarket.ee/entry/doktorivorst-300-g/4740003003269");
            expected1.setUnitPrice(new UnitPrice(4.30,Currency.EUR,Unit.KG));
            expected1.setRegularPrice(new Price(1.29d,Currency.EUR));


            //CHECK Regular Prices
            assertEquals(expected.getRegularPrice().getAmount(), p.getProductPrices().get(0).getRegularPrice().getAmount());
            assertEquals(expected1.getRegularPrice().getAmount(), p1.getProductPrices().get(0).getRegularPrice().getAmount());

            assertEquals(expected.getRegularPrice().getCurrency(), p.getProductPrices().get(0).getRegularPrice().getCurrency());
            assertEquals(expected1.getRegularPrice().getCurrency(), p1.getProductPrices().get(0).getRegularPrice().getCurrency());

            //CHECK Unit Prices
            assertEquals(expected.getUnitPrice().getAmount(), p.getProductPrices().get(0).getUnitPrice().getAmount());
            assertEquals(expected1.getUnitPrice().getAmount(), p1.getProductPrices().get(0).getUnitPrice().getAmount());

            assertEquals(expected.getUnitPrice().getCurrency(), p.getProductPrices().get(0).getUnitPrice().getCurrency());
            assertEquals(expected1.getUnitPrice().getCurrency(), p1.getProductPrices().get(0).getUnitPrice().getCurrency());

            assertEquals(expected.getUnitPrice().getPerUnit(), p.getProductPrices().get(0).getUnitPrice().getPerUnit());
            assertEquals(expected1.getUnitPrice().getPerUnit(), p1.getProductPrices().get(0).getUnitPrice().getPerUnit());

            //CHECK STORE
            assertEquals(expected.getStore(), p.getProductPrices().get(0).getStore());
            assertEquals(expected1.getStore(), p1.getProductPrices().get(0).getStore());

            //CHECK URL
            assertEquals(expected.getUrl(), p.getProductPrices().get(0).getUrl());
            assertEquals(expected1.getUrl(), p1.getProductPrices().get(0).getUrl());


        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testQuantity() {

        try {
            Product p = prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/coca-cola-zero-peach-karastusjook-1-5l/5449000272805");
            Product p1= prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/doktorivorst-300-g/4740003003269");

            Quantity expected = new Quantity();
            expected.setUnit(Unit.L);
            expected.setValue(1.5d);

            Quantity expected1 = new Quantity();
            expected1.setValue(0.3d);
            expected1.setUnit(Unit.KG);

            assertEquals(expected.getUnit(), p.getQuantity().getUnit());
            assertEquals(expected1.getUnit(), p1.getQuantity().getUnit());

            assertEquals(expected.getValue(), p.getQuantity().getValue());
            assertEquals(expected1.getValue(), p1.getQuantity().getValue());





        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }
}
