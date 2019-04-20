package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.Scraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.ScraperMain;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.model.ExceptionReport;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.scraper.repository.ScraperService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ScraperRunnable implements Runnable {

    private Scraper scraper;
    private ScraperService scraperService;
    private ScraperMain scraperMain;

    public ScraperRunnable(Scraper scraper, ScraperMain scraperMain) {
        super();
        this.scraper = scraper;
        this.scraperService = scraperMain.getScraperService();
        this.scraperMain = scraperMain;
    }

    private void updateDatabase() {
        for (Category c : Category.values()) {
            if (c == Category.UNKNOWN) {
                continue;
            }
            try {
                List<String> links = scraper.getCategoryLinks(c);
                for (String s : links) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    Product p = scraper.getProductFromPage(s);
                    p.setCategory(c);
                    scraperService.updateProduct(p);
                }
            } catch (ScraperException e) {
                scraperMain.getScraperReport().addExceptionReport(e.getExceptionReport());
            }
        }
    }

    private int fibonacci(int n)  {
        if (Thread.currentThread().isInterrupted()) {
            return -1;
        }
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    public void run() {
        try {
            System.out.println(this);
            System.out.println(fibonacci(40));
        } finally {
            scraperMain.notifyRunnableComplete();
        }
    }

    @Override
    public String toString() {
        return "Runnable for : " + scraper.getStore() + " : " + Thread.currentThread().toString();
    }

}
