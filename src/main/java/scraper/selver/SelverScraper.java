package scraper.selver;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scraper.Scraper;
import scraper.util.DocumentManager;
import scraper.util.RegexMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SelverScraper implements Scraper {

    private final SelverUrlManager urlManager = new SelverUrlManager("96");
    private final DocumentManager documentManager = new DocumentManager();

    public static void main(String[] args) {
        SelverScraper scraper = new SelverScraper();
        SelverUrlManager urlM = new SelverUrlManager("24");
        scraper.getProducts();
    }

    @Override
    public List<Product> getProducts() {
        return Arrays.stream(Category.values())
                .map(this::getProductsFromCategory)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private Stream<Product> getProductsFromCategory(Category category) {
        return IntStream.rangeClosed(1, getPageCount(urlManager.buildCategoryUrl(category)))
                .mapToObj(i -> getProductPages(documentManager.getDocument(urlManager.buildCategoryUrl(category, i))))
                .flatMap(Function.identity())
                .map(this::scrapeProductPage)
                .peek(p -> p.setCategory(category))
                .peek(System.out::println);
    }

    private int getPageCount(String url) {
        return Integer.parseInt(
                documentManager.getDocument(url)
                        .selectFirst("ol.pagination")
                        .selectFirst("a.last")
                        .html()
        );
    }

    private Stream<String> getProductPages(Document doc) {
        return doc.getElementById("products-grid")
                .children()
                .stream()
                .map(p -> p.selectFirst("a.product-image").attr("href"));
    }

    private Product scrapeProductPage(String url) {
        Document doc = documentManager.getDocument(url);
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
        if (tableData.containsKey("tootja") && !tableData.get("tootja").toLowerCase().equals("määramata")) {
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
        table.children().forEach(r -> tableData.put(
                r.selectFirst("th").html().toLowerCase(),
                r.selectFirst("td").html()
        ));
        return tableData;
    }

    // For testing purposes only
    @Deprecated
    private List<Product> simpleCategoryScraper(String url) {
        List<Product> products = new ArrayList<>();
        getProductPages(documentManager.getDocument(String.format(url, 1)))
                .parallel()
                .forEach(u -> products.add(scrapeProductPage(u)));
        return products;
    }

    // For testing purposes only
    @Deprecated
    public List<Product> getSampleData(Category category) {
        List<Product> products = simpleCategoryScraper(urlManager.buildCategoryUrl(category));
        products.forEach(p -> p.setCategory(category));
        return products;

    }

    // For testing purposes only
    @Deprecated
    public List<Product> getMoreSampleData() {
        List<Product> products = new ArrayList<>();
        for (Category category : Category.values()) {
            List<Product> scrapedProducts = simpleCategoryScraper(urlManager.buildCategoryUrl(category));
            scrapedProducts.forEach(p -> p.setCategory(category));
            products.addAll(scrapedProducts);
        }
        return products;
    }

    @Override
    public List<Product> getDemoData(Category category) {
        return getProductsFromCategory(category).collect(Collectors.toList());
    }

    @Override
    public Product getProductFromPage(String url) {
        return scrapeProductPage(url);
    }
}
