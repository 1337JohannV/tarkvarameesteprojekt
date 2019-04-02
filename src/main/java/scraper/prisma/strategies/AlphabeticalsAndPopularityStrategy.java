package scraper.prisma.strategies;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.util.DocumentManager;

import java.util.ArrayList;
import java.util.List;

public class AlphabeticalsAndPopularityStrategy implements PrismaCategoryScrapingStrategy {

    @Override
    public List<String> getProductUrlsFromCategory(String url, DocumentManager dm) {

        AZandZAStrategy previousStrategies = new AZandZAStrategy();

        List<String> previousLinks = new ArrayList<>(previousStrategies.getProductUrlsFromCategory(url, dm));

        List<String> links = new ArrayList();

        String searchUrl = url + "?sort_order=relevancy&sort_dir=desc";

        Document doc = dm.getDocument(searchUrl);

        Elements products = doc.select("li.item");
        for (Element product : products) {
            links.add(product.selectFirst("a.js-link-item").attr("href"));
        }

        for (String link : links) {
            if (!previousLinks.contains(link)) {
                previousLinks.add(link);
            }

        }
        System.out.println(previousLinks.size());
        return previousLinks;


    }
}
