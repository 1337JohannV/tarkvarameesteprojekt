package ee.ttu.tarkvaratehnika.andmekogujad.scraper.main;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Deprecated
public class ScraperMaster {

    public static void main(String[] args) {
        ScraperMaster scraperMaster = new ScraperMaster();

        //System.out.println(scraperMaster.scrapeProducts());

    }

    /*
    public List<Product> scrapeProducts() {

        DatabaseScraperThread prismaThread = new TestScraperThread(new PrismaScraper(), "https://www.prismamarket.ee/entry/4740036009498");
        DatabaseScraperThread selverThread = new TestScraperThread(new SelverScraper(), "https://www.selver.ee/hapukoor-20-tere-330-g");
        DatabaseScraperThread maximaThread = new TestScraperThread(new MaximaScraper(),"dd");

        prismaThread.start();
        selverThread.start();
        maximaThread.start();

        try {
            prismaThread.join();
            selverThread.join();
            maximaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Product> data = Stream.of(prismaThread.getProducts(), selverThread.getProducts(),maximaThread.getProducts())
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
    }*/

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
}
