package scraper.prisma;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.DocumentManager;

import java.util.ArrayList;
import java.util.List;

public class AZStrategy implements PrismaCategoryScrapingStrategy {

    @Override
    public List<String> getProductUrlsFromCategory(String url) {

        List<String> links = new ArrayList<>();

        String searchUrl = url + "?sort_order=alpha&sort_dir=asc";

        Document doc = DocumentManager.getDocument(searchUrl);

        Elements products = doc.select("li.item");
        for (Element product : products) {
            links.add(product.selectFirst("a.js-link-item").attr("href"));
        }
        return links;
    }
}
