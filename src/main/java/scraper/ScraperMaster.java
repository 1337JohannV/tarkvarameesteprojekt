package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import scraper.prisma.PrismaScraper;
import scraper.selver.SelverScraper;
import scraper.util.ProductMerger;
import scraper.util.thread.ScraperThread;
import scraper.util.thread.test.TestScraperThread;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScraperMaster {

    public List<Product> scrapeProducts() {

        ScraperThread prismaThread = new TestScraperThread(new PrismaScraper(), "https://www.prismamarket.ee/entry/4740036009498");
        ScraperThread selverThread = new TestScraperThread(new SelverScraper(), "https://www.selver.ee/hapukoor-20-tere-330-g");

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

        HashMap<String, List<Product>> eanMap = mapToEanMap(
                data
                .stream()
                .filter(p -> p.getEan() != null)
                .collect(Collectors.toList())
        ); //{key : ean , value : list of products with same ean}

        ProductMerger productMerger = new ProductMerger();

        List<Product> mergedProducts = eanMap
                .keySet()
                .stream()
                .map(e -> productMerger.mergeProducts(e, eanMap.get(e)))
                .collect(Collectors.toList());

        List<Product> productsWithoutEan = data.stream().filter(p -> p.getEan() == null).collect(Collectors.toList());

        return Stream.of(mergedProducts, productsWithoutEan).flatMap(List::stream).collect(Collectors.toList());
        }

    private HashMap<String, List<Product>> mapToEanMap(List<Product> data) {
        HashMap<String, List<Product>> eanMap = new HashMap<>();
        for (Product p : data) {
            List<Product> products = eanMap.get(p.getEan());

            if (products == null) {
                products = new ArrayList<>();
                products.add(p);
                eanMap.put(p.getEan(), products);
            } else {
                if (!products.contains(p)) {
                    products.add(p);
                }
            }
        }
        return eanMap;
    }

    public static void main(String[] args) {
        ScraperMaster scraperMaster = new ScraperMaster();

        System.out.println(scraperMaster.scrapeProducts());

    }
}
