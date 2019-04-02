package scraper.util.thread.test;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import scraper.Scraper;
import scraper.util.thread.ScraperThread;

import java.util.List;

public class TestScraperThread extends ScraperThread {

    private String url;

    public TestScraperThread(Scraper scraper, String url) {
        super(scraper);
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println("TEST SCRAPER RUNNING FOR SCRAPER:" + getScraper().toString());
        setProducts(getScraper().getDemoData(Category.JUUSTUD));
        //setProducts(Arrays.asList(getScraper().getProductFromPage(url)));
    }

    @Override
    public List<Product> getProducts() {
        return super.getProducts();
    }
}
