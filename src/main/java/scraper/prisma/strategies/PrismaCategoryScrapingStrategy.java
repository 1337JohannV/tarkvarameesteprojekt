package scraper.prisma.strategies;

import java.util.List;

public interface PrismaCategoryScrapingStrategy {

    List<String> getProductUrlsFromCategory(String url);

}
