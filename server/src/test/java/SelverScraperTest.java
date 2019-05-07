import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver.SelverScraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelverScraperTest {

    private SelverScraper selverScraper = new SelverScraper();

    @Test
    void testName() {
        try {

            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            String expected1 = "Eesti Juust täispiimast, viilutatud, TRADITSIOONILINE EESTI JUUST, 450 g";
            String expected2 = "Eestimaine broilerifilee, TALLEGG, kg";

            assertEquals(expected1,p1.getName());
            assertEquals(expected2,p2.getName());

        } catch (ScraperException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testOrigin() {

        try {

            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            String expected1 = "Eesti";
            String expected2 = "Eesti";

            assertEquals(expected1,p1.getOrigin());
            assertEquals(expected2,p2.getOrigin());

        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }
    @Test
    void testEan() {

        try {

            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            String expected1 = "4740572005244";
            String expected2 = "2702592000007";

            assertEquals(expected1,p1.getEan());
            assertEquals(expected2,p2.getEan());

        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }
    @Test
    void testProducer() {

        try {

            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            String expected1 = "ESTOVER PIIMATÖÖSTUS OÜ";
            String expected2 = "HKSCAN ESTONIA AS";

            assertEquals(expected1,p1.getProducer());
            assertEquals(expected2,p2.getProducer());

        } catch (ScraperException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testProductPrices() {

        try {

            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            ProductPrice expected1 = new ProductPrice();

            expected1.setStore(Store.SELVER);
            expected1.setUrl("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            expected1.setRegularPrice(new Price(4.59d, Currency.EUR));
            expected1.setUnitPrice(new UnitPrice(10.20d,Currency.EUR, Unit.KG));

            ProductPrice expected2 = new ProductPrice();

            expected2.setStore(Store.SELVER);
            expected2.setUrl("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");
            expected2.setRegularPrice(new Price(5.19d, Currency.EUR));
            expected2.setUnitPrice(new UnitPrice(5.19d, Currency.EUR,Unit.KG));

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

        } catch (ScraperException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testQuantity() {
        try {
            Product p1 = selverScraper.getProductFromPage("https://www.selver.ee/eesti-juust-taispiimast-viilutatud-traditsiooniline-eesti-juust-450-g");
            Product p2 = selverScraper.getProductFromPage("https://www.selver.ee/jahutatud-kanafilee-tallegg-kg");

            Quantity expected1 = new Quantity();
            expected1.setUnit(Unit.G);
            expected1.setValue(450d);

            Quantity expected2 = new Quantity();
            expected2.setValue(1d);
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
