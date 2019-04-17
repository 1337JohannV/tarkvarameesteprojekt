package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma;


import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.Scraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.strategies.AZStrategy;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.strategies.AZandZAStrategy;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.strategies.AlphabeticalsAndPopularityStrategy;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.prisma.strategies.PrismaCategoryScrapingStrategy;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.DocumentManager;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.RegexMatcher;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Price;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.ProductPrice;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrismaScraper implements Scraper {

    public static void main(String[] args) {
        PrismaScraper prismaScraper = new PrismaScraper();
        //System.out.println(prismaScraper.getDemoData(Category.JOOGID));
        //prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/pom-bel-ouna-mango-puuviljamiks--4x100-g/8437010537165");

        //System.out.println(PrismaUrlManager.getSubCatUrls(Category.PUU_JA_KOOGIVILJAD));
        //System.out.println(prismaScraper.scrapeCategory(Category.PUU_JA_KOOGIVILJAD));
        // System.out.println(prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/viinamari-victoria--i-klass/2060460600002"));
        //System.out.println(prismaScraper.getProductFromPage("https://www.prismamarket.ee/entry/ananass/2060490100008"));
        //System.out.println(prismaScraper.getProductUrlsFromCategory("https://www.prismamarket.ee/products/17097"));\
        // prismaScraper.getProducts();
        //System.out.println(DocumentManager.getDocument("https://ecoop.ee/et/kategooriad/kuivained-kastmed/"));

    }

    @Override
    public List<String> getCategoryLinks(Category category) throws ScraperException {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Scraper: " + Store.PRISMA;
    }

    /*
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Category c : Category.values()) {
            List<Product> productsFromCategory = scrapeCategory(c);
            productsFromCategory.forEach(p -> p.setCategory(c));
            products.addAll(productsFromCategory);
        }
        return products;
    }
    */

    private PrismaCategoryScrapingStrategy selectStrategy(Document doc) {
        int numberOfItemsOnPage = Integer.parseInt(doc.getElementsByClass("category-items")
                .first().text().split("\\s+", 2)[0]);
        if (numberOfItemsOnPage <= 48) {
            return new AZStrategy();
        }
        if (numberOfItemsOnPage <= 96) {
            return new AZandZAStrategy();
        } else {
            //Praegu ei leia kõiki tooteid, tuleks lisada see, et vaatab odavamad/kallimad ka, siis peaks loodetavasti
            //saama kõik tooted kätte.
            return new AlphabeticalsAndPopularityStrategy();
        }
    }

    private String getProductImgUrl(Document doc) {
        Element img = doc.getElementById("product-image-zoom");
        return img.absUrl("src");
    }

    public List<String> getProductUrlsFromCategory(String url) {
        Document doc = DocumentManager.getDocument(url);
        PrismaCategoryScrapingStrategy strat = selectStrategy(doc);
        return strat.getProductUrlsFromCategory(url);
    }

    public List<Product> demoCat(Category cat, int amount) {

        List<Product> products = new ArrayList<>();
        List<String> catUrls = PrismaUrlManager.getSubCatUrls(cat);
        assert catUrls != null;
        for (String url : catUrls) {
            List<String> productUrls = getProductUrlsFromCategory(url);
            for (String productUrl : productUrls) {
                if (amount < 1) {
                    break;
                }
                amount--;
                String searchUrl = "https://www.prismamarket.ee" + productUrl;
                Product product = getProductFromPage(searchUrl);
                product.setCategory(cat);
                products.add(product);
            }
            if (amount < 1) {
                break;
            }
        }
        return products;

    }

    public List<Product> scrapeCategory(Category cat) {
        List<Product> products = new ArrayList<>();
        List<String> catUrls = PrismaUrlManager.getSubCatUrls(cat);
        assert catUrls != null;
        for (String url : catUrls) {
            List<String> productUrls = getProductUrlsFromCategory(url);
            for (String productUrl : productUrls) {
                String searchUrl = "https://www.prismamarket.ee" + productUrl;
                Product product = getProductFromPage(searchUrl);
                product.setCategory(cat);
                products.add(product);
            }
        }
        return products;
    }

    private String getOrigin(Document doc) {
        Element info = doc.getElementById("info");
        if (!info.html().equals("")) {
            String origin = info.children().last().html().trim();
            if (origin.length() < 15) {
                return origin;
            }
        }
        return null;
    }

    private String getUnitPrice(Document doc) {
        if (!doc.selectFirst("span.unit").text().equals("")) {
            return new StringBuilder()
                    .append(getPrice(doc))
                    .append(" €")
                    .append(doc.selectFirst("span.unit").text())
                    .toString();
        }
        return doc.selectFirst(".js-details").text();
    }

    private String getProductUrl(Document doc) {
        return doc.getElementsByTag("link").first().attr("href");
    }

    private double getPrice(Document doc) {
        return Double.parseDouble(
                new StringBuilder()
                        .append(doc.getElementsByClass("whole-number").first().text())
                        .append(".")
                        .append(doc.getElementsByClass("decimal").first().text())
                        .toString());
    }

    private String getQuantity(Document doc) {
        return doc.getElementsByClass("js-quantity").first().text();
    }

    @Override
    public Product getProductFromPage(String url) {
        Document doc = DocumentManager.getDocument(url);

        String producer = doc.getElementById("product-subname").text();
        String productName = doc.getElementById("product-name").text();
        String ean = doc.select("span[itemprop = sku]").first().text();

        Product product = new Product();
        ProductPrice productPrice = new ProductPrice();
        Price price = new Price();

        product.setProducer(producer);
        product.setName(productName);
        product.setEan(ean);
        product.setOrigin(getOrigin(doc));
        product.setQuantity(RegexMatcher.extractQuantity(getQuantity(doc)));
        product.setImgUrl(getProductImgUrl(doc));

        price.setAmount(getPrice(doc));
        price.setCurrency(Currency.EUR);

        productPrice.setRegularPrice(price);
        productPrice.setStore(Store.PRISMA);
        productPrice.setUrl(getProductUrl(doc));
        productPrice.setUnitPrice(RegexMatcher.extractUnitPrice(getUnitPrice(doc)));

        product.setProductPrices(Arrays.asList(productPrice));

        System.out.println(product);
        return product;
    }

    @Override
    public Store getStore() {
        return Store.PRISMA;
    }

    /*
    @Override
    public List<Product> getDemoData(Category category) {

        int amount = 20;
        List<Product> demoData = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>(Arrays.asList(Category.values()));
        categories.remove(Category.UNKNOWN);
        for (Category c: categories) {
            demoData.addAll(demoCat(c,amount));
        }
      return demoData;
    }
    */

}
