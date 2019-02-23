package scraper.prisma;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.DocumentManager;

import java.util.ArrayList;
import java.util.List;

public class AlphabeticalsAndPopularityStrategy implements PrismaCategoryScrapingStrategy {

    @Override
    public List<String> getProductUrlsFromCategory(String url) {

        AZandZAStrategy previousStrategies = new AZandZAStrategy();

        List<String> previousLinks = new ArrayList<>(previousStrategies.getProductUrlsFromCategory(url));

        List<String> links = new ArrayList();

        String searchUrl = url + "?sort_order=relevancy&sort_dir=desc";

        Document doc = DocumentManager.getDocument(searchUrl);

        Elements products = doc.select("li.item");
        for (Element product : products) {
            links.add(product.selectFirst("a.js-link-item").attr("href"));
        }

        for (String link: links) {
            if(!previousLinks.contains(link)) {
                previousLinks.add(link);
            }

        }
        System.out.println(previousLinks.size());
        return previousLinks;


    }
}
