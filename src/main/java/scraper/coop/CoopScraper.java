package scraper.coop;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
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




}
