package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;

public class PrismaScraper {

    public static void example(Document doc) {
        Elements products = doc.select("li.item");
        for (Element product : products) {
            System.out.println(product.attr("data-ean"));
            System.out.println(product.selectFirst("a.js-link-item").attr("href"));
        }
    }

    public static void main(String[] args) {
        PrismaScraper.example(DocumentManager.getDocument("https://www.prismamarket.ee/products/19273"));
    }
}
