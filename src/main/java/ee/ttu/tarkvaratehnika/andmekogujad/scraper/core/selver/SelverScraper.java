package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.Scraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.DocumentManager;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.RegexMatcher;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Price;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.ProductPrice;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SelverScraper implements Scraper {

    public static void main(String[] args) {
    }

    @Override
    public Store getStore() {
        return Store.SELVER;
    }

    @Override
    public String toString() {
        return "Scraper: " + Store.SELVER;
    }

    @Override
    public List<String> getCategoryLinks(Category category) throws ScraperException {
        try {
            return IntStream.rangeClosed(1, getPageCount(SelverUrlManager.buildCategoryUrl(category)))
                    .mapToObj(
                            i -> getProductPages(DocumentManager.getDocument(
                                    SelverUrlManager.buildCategoryUrl(category, i)))
                    )
                    .flatMap(Function.identity())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ScraperException(this, null, "Exception while getting category links", e);
        }
    }

    @Override
    public Product getProductFromPage(String url) throws ScraperException {
        try {
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
        } catch (Exception e) {
            throw new ScraperException(this, url, "Exception while scraping product page", e);
        }
    }

    private HashMap<String, String> extractTableData(Element table) {
        HashMap<String, String> tableData = new HashMap<>();
        table.children().forEach(r -> tableData.put(
                r.selectFirst("th").html().toLowerCase(),
                r.selectFirst("td").html()
        ));
        return tableData;
    }

    private int getPageCount(String url) {
        return Integer.parseInt(
                DocumentManager.getDocument(url)
                        .selectFirst("ol.pagination")
                        .selectFirst("li:nth-last-child(2)")
                        .text()
        );
    }

    private Stream<String> getProductPages(Document doc) {
        return doc.getElementById("products-grid")
                .children()
                .stream()
                .map(p -> p.selectFirst("a.product-image").attr("href"));
    }

      /*
    // For testing purposes only
    @Deprecated
    private List<Product> simpleCategoryScraper(String url) {
        List<Product> products = new ArrayList<>();
        getProductPages(DocumentManager.getDocument(String.format(url, 1)))
                .parallel()
                .forEach(u -> products.add(getProductFromPage(u)));
        return products;
    }

    // For testing purposes only
    @Deprecated
    public List<Product> getSampleData(Category category) {
        List<Product> products = simpleCategoryScraper(SelverUrlManager.buildCategoryUrl(category));
        products.forEach(p -> p.setCategory(category));
        return products;

    }

    // For testing purposes only
    @Deprecated
    public List<Product> getMoreSampleData() {
        List<Product> products = new ArrayList<>();
        for (Category category : Category.values()) {
            List<Product> scrapedProducts = simpleCategoryScraper(SelverUrlManager.buildCategoryUrl(category));
            scrapedProducts.forEach(p -> p.setCategory(category));
            products.addAll(scrapedProducts);
        }
        return products;
    }

    @Override
    public List<Product> getDemoData(Category category) {
        return getProductsFromCategory(category).collect(Collectors.toList());
    }
    */
      /*
    @Override
    public List<Product> getProducts() {
        return Arrays.stream(Category.values())
                .map(this::getProductsFromCategory)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }
    /*

    /*
    private Stream<Product> getProductsFromCategory(Category category) {
        return IntStream.rangeClosed(1, getPageCount(SelverUrlManager.buildCategoryUrl(category)))
                .mapToObj(i -> getProductPages(DocumentManager.getDocument(SelverUrlManager.buildCategoryUrl(category, i))))
                .flatMap(Function.identity())
                .map(this::scrapeProductPage)
                .peek(p -> p.setCategory(category))
                .peek(System.out::println);
    }
    */
}