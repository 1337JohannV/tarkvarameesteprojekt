package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.ScraperMain;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.service.scraper.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class MaximaRunnable implements Runnable {

    @Autowired
    private MaximaScraper maximaScraper;

    @Autowired
    private ScraperMain scraperMain;

    @Autowired
    private ScraperService scraperService;

    private void updateDatabase() {
        String nullProductLink = null;
        for (Category c : Category.values()) {
            if (c == Category.UNKNOWN) {
                continue;
            }
            try {
                List<String> links = maximaScraper.getCategoryLinks(c);
                for (String s : links) {
                    Product p = maximaScraper.getProductFromPage(s);
                    if (p == null) {
                        nullProductLink = s;
                        throw new NullPointerException();
                    }
                    p.setCategory(c);
                    scraperService.updateProduct(p);
                }
            } catch (NullPointerException e) {
                scraperMain.getScraperReport()
                        .addExceptionReport(
                                new ScraperException(
                                        Store.MAXIMA, nullProductLink, "Scraped product is null", e
                                ).getExceptionReport());
            } catch (ScraperException e) {
                scraperMain.getScraperReport().addExceptionReport(e.getExceptionReport());
                System.out.println("Exception occurred while scraping");
                System.out.println(e.getExceptionReport());
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this);
        updateDatabase();
    }
}
