package scraper.prisma.strategies;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.util.DocumentManager;

import java.util.ArrayList;
import java.util.List;

public class AZStrategy implements PrismaCategoryScrapingStrategy {

    @Override
    public List<String> getProductUrlsFromCategory(String url, DocumentManager dm) {

        List<String> links = new ArrayList<>();

        String searchUrl = url + "?sort_order=alpha&sort_dir=asc";

        Document doc = dm.getDocument(searchUrl);

        Elements products = doc.select("li.item");
        for (Element product : products) {
            links.add(product.selectFirst("a.js-link-item").attr("href"));
        }
        return links;
    }
}
