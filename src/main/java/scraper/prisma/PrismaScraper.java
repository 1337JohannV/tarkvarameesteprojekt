package scraper.prisma;


import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import org.jsoup.nodes.Document;
import scraper.DocumentManager;
import scraper.RegexMatcher;
import scraper.Scraper;
import scraper.prisma.strategies.AZStrategy;
import scraper.prisma.strategies.AZandZAStrategy;
import scraper.prisma.strategies.AlphabeticalsAndPopularityStrategy;
import scraper.prisma.strategies.PrismaCategoryScrapingStrategy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class PrismaScraper implements Scraper {


    @Override
    public HashMap<Category, List<Product>> scrapeCategories() {
        long t1 =System.currentTimeMillis();
        HashMap<Category,List<Product>> productsFromEachCategory = new HashMap<>();

        for (Category c: Category.values()) {
            productsFromEachCategory.put(c,scrapeCategory(c));
        }

        long t2 = System.currentTimeMillis();
        System.out.println("TIME");
        System.out.println((t2 - t1) / 1000);
        return productsFromEachCategory;
    }

    private PrismaCategoryScrapingStrategy selectStrategy(Document doc) {

        int numberOfItemsOnPage = Integer.parseInt(doc.getElementsByClass("category-items")
                .first().text().split("\\s+", 2)[0]);
        System.out.println(numberOfItemsOnPage);

        if (numberOfItemsOnPage <= 48) {
            System.out.println("alphabeticalOnce");
            return new AZStrategy();
        }
        if (numberOfItemsOnPage <= 96) {
            System.out.println("alphabeticalTwice");
            return new AZandZAStrategy();

        } else {
            //Praegu ei leia kõiki tooteid, tuleks lisada see, et vaatab odavamad/kallimad ka, siis peaks loodetavasti
            //saama kõik tooted kätte.

            System.out.println("HopingForTheBest");
            return new AlphabeticalsAndPopularityStrategy();
        }

    }

    public List<String> getProductUrlsFromCategory(String url) {

        Document doc = DocumentManager.getDocument(url);

        PrismaCategoryScrapingStrategy strat = selectStrategy(doc);

        return strat.getProductUrlsFromCategory(url);


    }

    private List<Product> scrapeCategory(Category cat) {

        List<Product> products = new ArrayList<>();
        List<String> catUrls = PrismaUrlManager.getSubCatUrls(cat);

        assert catUrls != null;

        for (String url : catUrls) {

            List<String> productUrls = getProductUrlsFromCategory(url);

            for (String productUrl : productUrls) {

                String searchUrl = "https://www.prismamarket.ee" + productUrl;

                Product product = getProductDetails(searchUrl);
                product.setCategory(cat);

                products.add(product);
            }

        }
        System.out.println(products.size());
        return products;


    }

    private String getOrigin(Document doc) {
        String origin;
        try {

            origin = doc.getElementsByClass("tab-content padding").first().text().split("Toitumisalane")[0]
                    .split("Päritolumaa")[1];
            return origin;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unknown origin");
            return "-";
        }
    }

    private String getUnitPrice(Document doc) {

        String unitPrice;
        if (!doc.getElementsByClass("js-details").first().text().contains("~")) {
            if (!doc.getElementsByClass("js-details").first().text().toLowerCase().contains("kampaania")
                    && doc.getElementsByClass("js-details").first().text().toLowerCase().contains("/")) {
                unitPrice = doc.getElementsByClass("js-details")
                        .first().text().split("\\s+", 3)[2];
                return unitPrice;
            }
            if (doc.getElementsByClass("js-details").first().text().toLowerCase().contains("kampaania")) {
                unitPrice = doc.getElementsByClass("js-details")
                        .first().text().split("\\s+", 5)[2] +
                        " " + doc.getElementsByClass("js-details")
                        .first().text().split("\\s+", 5)[3];
                return unitPrice;

            } else {
                return getPrice(doc) + "€/" + getQuantity(doc).split("\\s+")[1];
            }
        } else {
            return getPrice(doc) + "€/" + getQuantity(doc).split("\\s+")[1];

        }


    }

    private String getProductUrl(Document doc) {
        return doc.getElementsByTag("link").first().attr("href");

    }

    private double getPrice(Document doc) {
        return Double.parseDouble(doc.getElementsByClass("whole-number").first().text()
                + "." + doc.getElementsByClass("decimal").first().text());
    }

    private String getQuantity(Document doc) {
        return doc.getElementsByClass("js-quantity").first().text();
    }

    public Product getProductDetails(String url) {

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

        price.setAmount(getPrice(doc));
        price.setCurrency(Currency.EUR);

        productPrice.setRegularPrice(price);
        productPrice.setStore(Store.PRISMA);
        productPrice.setUrl(getProductUrl(doc));
        System.out.println(productName);
        productPrice.setUnitPrice(RegexMatcher.extractUnitPrice(getUnitPrice(doc)));

        product.setProductPrices(Arrays.asList(productPrice));


        return product;
    }

    public static void main(String[] args) {
        PrismaScraper prismaScraper = new PrismaScraper();
        //System.out.println(prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/mandlitukkide-ja-sokolaadiga-jogurt--150-g/4740125539042"));
        //System.out.println(PrismaUrlManager.getSubCatUrls(Category.PUU_JA_KOOGIVILJAD));
        System.out.println(prismaScraper.scrapeCategory(Category.PUU_JA_KOOGIVILJAD));
       // System.out.println(prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/viinamari-victoria--i-klass/2060460600002"));
        //System.out.println(prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/ananass/2060490100008"));
        //System.out.println(prismaScraper.getProductUrlsFromCategory("https://www.prismamarket.ee/products/17097"));\
        //prismaScraper.scrapeCategories();

    }
}
