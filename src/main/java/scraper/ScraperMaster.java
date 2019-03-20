package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import scraper.prisma.PrismaScraper;
import scraper.selver.SelverScraper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScraperMaster {

    public List<Product> scrapeProducts(Scraper prisma, Scraper selver) {
        HashMap<String, List<Product>> eanMap = new HashMap<>();

        ScraperThread prismaThread = new ScraperThread(prisma);
        ScraperThread selverThread = new ScraperThread(selver);

        prismaThread.start();
        selverThread.start();

        try {
            prismaThread.join();
            selverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Product> data = Stream.of(prismaThread.getProducts(), selverThread.getProducts())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        appendToEanMap(eanMap, data);

        return eanMap
            .values()
            .parallelStream()
            .map(this::mergeProducts)
            .collect(Collectors.toList());
        }

    private void appendToEanMap(HashMap<String, List<Product>> eanMap, List<Product> data) {
        for (Product p : data) {
            if (eanMap.containsKey(p.getEan())) {
                List<Product> products = eanMap.get(p.getEan());
                products.add(p);
                eanMap.replace(p.getEan(), products);
            } else {
                eanMap.put(p.getEan(), Arrays.asList(p));
            }
        }
    }

    private Product mergeProducts(List<Product> products) {
        //TODO merge the fucking products
        return products.get(0);
    }

    public static void main(String[] args) {
        SelverScraper selverScraper = new SelverScraper();
        PrismaScraper prismaScraper = new PrismaScraper();
        ScraperMaster scraperMaster = new ScraperMaster();

        scraperMaster.scrapeProducts(prismaScraper, selverScraper);

    }
}
