package scraper.prisma;

import org.jsoup.nodes.Document;

import java.util.List;

public interface PrismaCategoryScrapingStrategy {

    List<String> getProductUrlsFromCategory(String url);

}
