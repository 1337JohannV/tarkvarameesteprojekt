package scraper.util.thread;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import lombok.Getter;
import lombok.Setter;
import scraper.Scraper;

import java.util.List;

public class ScraperThread extends Thread {

    @Getter
    private Scraper scraper;

    @Setter
    @Getter
    private List<Product> products;

    public ScraperThread(Scraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public void run() {
        System.out.println("new thread for" + scraper.toString());
        this.products = scraper.getProducts();
    }
}
