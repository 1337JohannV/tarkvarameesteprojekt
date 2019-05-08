
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima.MaximaScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MaximaScraperTest {
    private MaximaScraper maximaScraper = new MaximaScraper();

    @Test
    void testName(){
        try {
        Product p1 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
        Product p2 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");

        String expected1 = "Keefir GEFILUS 2,5% 1kg, PP";
        String expected2 = "Krevetid soolvees VICI, 200g";

        assertEquals(expected1,p1.getName());
        assertEquals(expected2,p2.getName());

        }
        catch (ScraperException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testOrigin(){
        try {
            Product p1 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
            Product p2 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");

            String expected1 = "Eesti";
            String expected2 = "Leedu";

            assertEquals(expected1,p1.getOrigin());
            assertEquals(expected2,p2.getOrigin());

        }
        catch (ScraperException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testProducer() {

        try {
            Product p1 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
            Product p2 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");

            String expected1 = " Valio Gefilus ";
            String expected2 = " Viciunai Baltic OÜ ";

            assertEquals(expected1,p1.getProducer());
            assertEquals(expected2,p2.getProducer());

        }
        catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testProductPrices() {

        try {
            Product p1 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
            Product p2 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");

            ProductPrice expected1 = new ProductPrice();

            expected1.setStore(Store.MAXIMA);
            expected1.setUrl("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
            expected1.setRegularPrice(new Price(0.95d, Currency.EUR));
            expected1.setUnitPrice(new UnitPrice(0.95d,Currency.EUR, Unit.KG));

            ProductPrice expected2 = new ProductPrice();

            expected2.setStore(Store.MAXIMA);
            expected2.setUrl("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");
            expected2.setRegularPrice(new Price(3.49d, Currency.EUR));
            expected2.setUnitPrice(new UnitPrice(17.45d, Currency.EUR,Unit.KG));

            //Regular price
            assertEquals(expected1.getRegularPrice().getAmount(),p1.getProductPrices().get(0).getRegularPrice().getAmount());
            assertEquals(expected2.getRegularPrice().getAmount(),p2.getProductPrices().get(0).getRegularPrice().getAmount());

            //Unit price
            assertEquals(expected1.getUnitPrice().getAmount(),p1.getProductPrices().get(0).getUnitPrice().getAmount());
            assertEquals(expected2.getUnitPrice().getAmount(),p2.getProductPrices().get(0).getUnitPrice().getAmount());

            assertEquals(expected1.getUnitPrice().getCurrency(), p1.getProductPrices().get(0).getUnitPrice().getCurrency());
            assertEquals(expected2.getUnitPrice().getCurrency(), p2.getProductPrices().get(0).getUnitPrice().getCurrency());

            assertEquals(expected1.getUnitPrice().getPerUnit(), p1.getProductPrices().get(0).getUnitPrice().getPerUnit());
            assertEquals(expected2.getUnitPrice().getPerUnit(), p2.getProductPrices().get(0).getUnitPrice().getPerUnit());

            //CHECK STORE
            assertEquals(expected1.getStore(), p1.getProductPrices().get(0).getStore());
            assertEquals(expected2.getStore(), p2.getProductPrices().get(0).getStore());

            //CHECK URL
            assertEquals(expected1.getUrl(), p1.getProductPrices().get(0).getUrl());
            assertEquals(expected2.getUrl(), p2.getProductPrices().get(0).getUrl());
        }
        catch (ScraperException e) {
            e.printStackTrace();
        }


    }
    @Test
    void testQuantity() {

        try {
            Product p1 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/keefir-gefilus-2-5-protsent-1-kg-pp");
            Product p2 = maximaScraper.getProductFromPage("https://www.barbora.ee/toode/krevetid-soolvees-vici-200-g");

            Quantity expected1 = new Quantity();
            expected1.setUnit(Unit.G);
            expected1.setValue(1000d);

            Quantity expected2 = new Quantity();
            expected2.setValue(200d);
            expected2.setUnit(Unit.G);

            assertEquals(expected1.getUnit(), p1.getQuantity().getUnit());
            assertEquals(expected2.getUnit(), p2.getQuantity().getUnit());

            assertEquals(expected1.getValue(), p1.getQuantity().getValue());
            assertEquals(expected2.getValue(), p2.getQuantity().getValue());

        }
        catch (ScraperException e) {
            e.printStackTrace();
        }

    }

}
