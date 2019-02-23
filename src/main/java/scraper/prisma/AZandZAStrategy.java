package scraper.prisma;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.DocumentManager;

import java.util.ArrayList;
import java.util.List;

public class AZandZAStrategy implements PrismaCategoryScrapingStrategy {

    @Override
    public List<String> getProductUrlsFromCategory(String url) {

        AZStrategy ascending = new AZStrategy();


        List<String> linksAscending = new ArrayList<>(ascending.getProductUrlsFromCategory(url));
        List<String> linksDescending = new ArrayList<>();

        String searchUrl = url + "?sort_order=alpha&sort_dir=desc";

        Document doc = DocumentManager.getDocument(searchUrl);


        Elements products = doc.select("li.item");
        for (Element product : products) {
            linksDescending.add(product.selectFirst("a.js-link-item").attr("href"));
        }
        for (String link : linksDescending) {
            if (!linksAscending.contains(link)) {
                linksAscending.add(link);
            }

        }
        System.out.println(linksAscending.size());
        return linksAscending;


    }
}
