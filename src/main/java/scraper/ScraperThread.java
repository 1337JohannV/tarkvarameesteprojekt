package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;

import java.util.List;

public class ScraperThread extends Thread {

    private Scraper scraper;
    private List<Product> products;

    public ScraperThread(Scraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public void run() {
        System.out.println("new thread for" + scraper.toString());
        this.products = scraper.getProducts();
    }

    public List<Product> getProducts() {
        return products;
    }
}
