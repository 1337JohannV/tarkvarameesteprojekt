package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import scraper.prisma.PrismaScraper;
import scraper.selver.SelverScraper;
import scraper.util.ProductMerger;
import scraper.util.ScraperThread;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScraperMaster {

    public List<Product> scrapeProducts() {

        ScraperThread prismaThread = new ScraperThread(new PrismaScraper());
        ScraperThread selverThread = new ScraperThread(new SelverScraper());

        prismaThread.start();
        selverThread.start();

        try {
            prismaThread.join();
            selverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Product> data = Stream.of(prismaThread.getProducts(), selverThread.getProducts())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        HashMap<String, List<Product>> eanMap = mapToEanMap(data); //{key : ean , value : list of products with same ean}
        ProductMerger productMerger = new ProductMerger();

        return eanMap
                .keySet()
                .stream()
                .map(e -> productMerger.mergeProducts(e, eanMap.get(e)))
                .collect(Collectors.toList());
        }

    private HashMap<String, List<Product>> mapToEanMap(List<Product> data) {
        HashMap<String, List<Product>> eanMap = new HashMap<>();
        for (Product p : data) {
            if (eanMap.containsKey(p.getEan())) {
                List<Product> products = eanMap.get(p.getEan());
                products.add(p);
                eanMap.replace(p.getEan(), products);
            } else {
                eanMap.put(p.getEan(), Arrays.asList(p));
            }
        }
        return eanMap;
    }

    public static void main(String[] args) {
        ScraperMaster scraperMaster = new ScraperMaster();

        scraperMaster.scrapeProducts();

    }
}
