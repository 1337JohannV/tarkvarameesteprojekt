package scraper.prisma.strategies;

import scraper.util.DocumentManager;

import java.util.List;

public interface PrismaCategoryScrapingStrategy {

    List<String> getProductUrlsFromCategory(String url, DocumentManager dm);

}
