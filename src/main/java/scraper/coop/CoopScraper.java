package scraper.coop;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.jsoup.nodes.Document;
import scraper.Scraper;
import java.util.List;

public class CoopScraper implements Scraper {

    @Override
    public List<Product> getProducts() {
        return null;
    }

    public void getDoc(String url){

    }

    public static void main(String[] args) {
        CoopScraper cs = new CoopScraper();
    }

    @Override
    public Product getProductFromPage(String url) {
        return null;
    }

    @Override
    public List<Product> getDemoData(Category category) {
        return null;
    }



}
