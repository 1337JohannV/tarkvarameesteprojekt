package scraper.coop;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.jsoup.nodes.Document;
import scraper.Scraper;
import scraper.util.DocumentManager;

import java.util.List;

public class CoopScraper implements Scraper {

    @Override
    public List<Product> getProducts() {
        return null;
    }

    public Document getDoc(String url){
        return DocumentManager.getDocument(url);

    }

    public static void main(String[] args) {
        CoopScraper cs = new CoopScraper();
        System.out.println(cs.getDoc("https://ecoop.ee/et/kategooriad/puu-ja-koogiviljad/"));    }

}
