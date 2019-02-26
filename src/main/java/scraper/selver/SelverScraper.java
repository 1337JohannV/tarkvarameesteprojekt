package scraper.selver;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.DocumentManager;
import scraper.RegexMatcher;
import scraper.Scraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SelverScraper implements Scraper {

    @Override
    public HashMap<Category, List<Product>> scrapeCategories() {
        HashMap<Category, List<Product>> productsByCategory = new HashMap<>();
        for (Category category : Category.values()) {
            productsByCategory.put(
                    category,
                    scrapeCategory(SelverUrlManager.buildCategoryUrl(category))
            );
        }
        return productsByCategory;
    }

    private List<Product> scrapeCategory(String url) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= getPageCount(url); i++) {
            getUrls(DocumentManager.getDocument(String.format(url, i)))
                    .parallelStream()
                    .forEach(u -> products.add(scrapeProductPage(u)));
        }
        return products;
    }

    private int getPageCount(String url) {
        return Integer.parseInt(
                DocumentManager.getDocument(url)
                        .selectFirst("ol.pagination")
                        .selectFirst("a.last")
                        .html()
        );
    }

    private List<String> getUrls(Document doc) {
        Elements products = doc.getElementById("products-grid").children();
        List<String> urls = new ArrayList<>();
        for (Element product : products) {
            urls.add(product.selectFirst("a.product-image").attr("href"));
        }
        return urls;
    }

    private Product scrapeProductPage(String url) {
        Document doc = DocumentManager.getDocument(url);

        Product product = new Product();
        ProductPrice productPrice = new ProductPrice();

        product.setName(doc.selectFirst("div.page-title h1").html());
        product.setQuantity(RegexMatcher.extractQuantity(product.getName()));

        Element priceBox = doc.selectFirst("div.price-box span.price");
        Element offer = doc.selectFirst("div[itemprop=offers]");

        productPrice.setStore(Store.SELVER);
        productPrice.setUrl(url);

        Price regularPrice = new Price();
        regularPrice.setAmount(Double.valueOf(
                offer.selectFirst("span[itemprop=price]").attr("content")
        ));
        regularPrice.setCurrency(RegexMatcher.matchCurrency(
                offer.selectFirst("span[itemprop=priceCurrency]").attr("content")
        ));

        productPrice.setRegularPrice(regularPrice);
        productPrice.setUnitPrice(RegexMatcher.extractUnitPrice(
                priceBox.selectFirst("span.unit-price").html()
        ));

        HashMap<String, String> tableData = extractTableData(
                doc.selectFirst("table.product-attributes tbody")
        );
        if (tableData.containsKey("ribakood")) {
            product.setEan(tableData.get("ribakood"));
        }
        if (tableData.containsKey("tootja") && !tableData.get("tootja").equals("määramata")) {
            product.setProducer(tableData.get("tootja"));
        }
        if (tableData.containsKey("päritolumaa")) {
            product.setOrigin(tableData.get("päritolumaa"));
        }
        if (tableData.containsKey("hind partnerkaardiga")) {
            productPrice.setSpecialPrice(RegexMatcher.extractPrice(
                    tableData.get("hind partnerkaardiga")
            ));
        }

        product.setProductPrices(Arrays.asList(productPrice));
        return product;
    }

    private HashMap<String, String> extractTableData(Element table) {
        HashMap<String, String> tableData = new HashMap<>();
        for (Element row : table.children()) {
            tableData.put(
                    row.selectFirst("th").html().toLowerCase(),
                    row.selectFirst("td").html()
            );
        }
        return tableData;
    }

    // For testing purposes only
    private List<Product> simpleCategoryScraper(String url) {
        List<Product> products = new ArrayList<>();
        getUrls(DocumentManager.getDocument(String.format(url, 1)))
                .parallelStream()
                .forEach(u -> products.add(scrapeProductPage(u)));
        return products;
    }

    // For testing purposes only
    public HashMap<Category, List<Product>> getSampleData() {
        HashMap<Category, List<Product>> productsByCategory = new HashMap<>();
        productsByCategory.put(
                Category.LIHA_JA_KALA,
                simpleCategoryScraper(SelverUrlManager.buildCategoryUrl(Category.LIHA_JA_KALA))
        );
        return productsByCategory;
    }

    // For testing purposes only
    public HashMap<Category, List<Product>> getMoreSampleData() {
        HashMap<Category, List<Product>> productsByCategory = new HashMap<>();
        for (Category category : Category.values()) {
            productsByCategory.put(
                    category,
                    simpleCategoryScraper(SelverUrlManager.buildCategoryUrl(category))
            );
        }
        return productsByCategory;
    }

    public static void main(String[] args) {
        SelverScraper scraper = new SelverScraper();
        double start = System.currentTimeMillis();
        scraper.scrapeProductPage("https://www.selver.ee/pulgakomm-fruit-with-juice-cola-chupa-chups-12-g");
        System.out.println((System.currentTimeMillis() - start) / 1000);
    }
}
