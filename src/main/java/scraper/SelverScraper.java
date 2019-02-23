package scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.model.Price;
import scraper.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelverScraper {

    private List<String> getUrls(Document doc) {
        Elements products = doc.getElementById("products-grid").children();
        List<String> urls = new ArrayList<>();
        for (Element product : products) {
            Element url = product.selectFirst("a.product-image");
            urls.add(url.attr("href"));
        }
        return urls;
    }

    private Product scrapeProductPage(String url) {
        Document doc = DocumentManager.getDocument(url);

        Product product = new Product();
        Price price = new Price();

        product.setName(doc.selectFirst("div.page-title h1").html());
        product.setUrl(url);

        Element priceBox = doc.selectFirst("div.price-box span.price");
        Element offer = doc.selectFirst("div[itemprop=offers]");

        price.setStore("Selver");
        price.setAmount(Double.valueOf(offer.selectFirst("span[itemprop=price]").attr("content")));
        price.setCurrency(offer.selectFirst("span[itemprop=priceCurrency]").attr("content"));
        price.setUnitPrice(priceBox.selectFirst("span.unit-price").html());
        product.setPrice(price);

        HashMap<String, String> tableData = extractTableData(doc.selectFirst("table.product-attributes tbody"));

        if (tableData.containsKey("ribakood")) product.setEan(tableData.get("ribakood"));
        if (tableData.containsKey("tootja")) product.setProducer(tableData.get("tootja"));
        if (tableData.containsKey("päritolumaa")) product.setOrigin(tableData.get("päritolumaa"));
        if (tableData.containsKey("hind partnerkaardiga")) {
            price.setSpecialPrice(tableData.get("hind partnerkaardiga"));
        }

        System.out.println(product);
        return product;
    }

    private HashMap<String, String> extractTableData(Element table) {
        HashMap<String, String> tableData = new HashMap<>();
        for (Element row : table.children()) {
            String key = row.selectFirst("th").html().toLowerCase();
            String value = row.selectFirst("td").html();
            tableData.put(key, value);
        }
        return tableData;
    }

    private int getPageCount(String url) {
        Document doc = DocumentManager.getDocument(url);
        Element pagination = doc.selectFirst("ol.pagination");
        Element lastPage = pagination.selectFirst("a.last");
        return Integer.parseInt(lastPage.html());
    }

    private List<Product> scrapeCategory(String url) {
        List<Product> products = new ArrayList<>();
        int pageCount = getPageCount(url);
        for (int i = 1; i <= pageCount; i++) {

            int productsSize = products.size();

            System.out.println(String.format("scraping page %d", i));

            getUrls(DocumentManager.getDocument(String.format(url, i)))
                    .parallelStream()
                    .forEach(u -> products.add(scrapeProductPage(u)));

            System.out.println(String.format("page finished, scraped %d products", products.size() - productsSize));
        }
        return products;
    }


    HashMap<UrlManager.Category, List<Product>> scrapeCategories() {
        HashMap<UrlManager.Category, List<Product>> productsByCategory = new HashMap<>();
        for (UrlManager.Category category : UrlManager.Category.values()) {

            System.out.println("scraping category: " + category);

            List<Product> products = scrapeCategory(UrlManager.buildCategoryUrl(category));

            System.out.println(String.format("category finished, scraped %d products\n\n", products.size()));

            productsByCategory.put(category, products);
        }
        return productsByCategory;
    }

    public static void main(String[] args) throws IOException {

        SelverScraper selverScraper = new SelverScraper();
        //selverScraper.getPageCount(UrlManager.buildCategoryUrl(UrlManager.Category.PUU_JA_KOOGIVILJAD));
        /**
        HashMap<UrlManager.Category, List<Product>> products = selverScraper.scrapeCategories();
        for (UrlManager.Category category : products.keySet()) {
            System.out.println(category);
            for (Product product : products.get(category)) {
                System.out.println(product);
            }
            System.out.println("\n\n");
        }
        **/
        //selverScraper.scrapeCategory(UrlManager.buildCategoryUrl(UrlManager.Category.VALMISTOIDUD));
        //selverScraper.getPageCount(UrlManager.buildCategoryUrl(UrlManager.Category.VALMISTOIDUD));

        selverScraper.scrapeProductPage("https://www.selver.ee/r-maapahkel-sinihallitusjuustu-taffel-150-g");


    }
}
