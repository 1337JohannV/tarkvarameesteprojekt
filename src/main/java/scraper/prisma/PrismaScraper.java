package scraper.prisma;


import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.DocumentManager;
import scraper.Scraper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PrismaScraper implements Scraper {


    @Override
    public HashMap<Category, List<Product>> scrapeCategories() {
        return null;
    }

    private PrismaCategoryScrapingStrategy selectStrategy(Document doc) {

        int numberOfItemsOnPage = Integer.parseInt(doc.getElementsByClass("category-items")
                .first().text().split("\\s+",2)[0]);
        System.out.println(numberOfItemsOnPage);

        if (numberOfItemsOnPage <= 48) {
            System.out.println("alphabeticalOnce");
            return new AZStrategy();
        }
        if(numberOfItemsOnPage <= 96) {
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

    private String getOrigin(Document doc) {
        Elements meta = doc.getElementsByTag("meta");
        for (Element el : meta) {
            String content = el.attr("content");
            if (content.contains("päritolumaa")) {
                return content.split("päritolumaa")[1].split("\\s+", 4)[1];
            }

        }
        return "";
    }

    private String getUnitPrice(Document doc) {

        String unitPrice;

        if (!doc.getElementsByClass("js-details").first().text().toLowerCase().contains("kampaania")) {
            unitPrice = doc.getElementsByClass("js-details").first().text().split("\\s+", 3)[2];
        } else {
            unitPrice = doc.getElementsByClass("js-details").first().text().split("\\s+", 5)[2] +
                    " " + doc.getElementsByClass("js-details").first().text().split("\\s+", 5)[3];
        }

        return unitPrice;

    }

    private String getProductUrl(Document doc) {
        return doc.getElementsByTag("link").first().attr("href");

    }

    private double getPrice(Document doc) {
        return Double.parseDouble(doc.getElementsByClass("whole-number").first().text()
                + "." + doc.getElementsByClass("decimal").first().text());
    }

    public Product getProductDetails(String url) {

        Document doc = DocumentManager.getDocument(url);

        String producer = doc.getElementById("product-subname").text();

        String productName = doc.getElementById("product-name").text();

        String ean = doc.select("span[itemprop = sku]").first().text();

        String quantity = doc.getElementsByClass("js-quantity").first().text();


        Product product = new Product();
        Price price = new Price();

        product.setProducer(producer);
        product.setName(productName);
        product.setEan(ean);
        product.setOrigin(getOrigin(doc));


        //price.setCurrency("€");
        //price.setStore(Store.PRISMA);
        //price.setAmount(getPrice(doc));
        //price.setQuantity(quantity);
        //price.setUnitPrice(getUnitPrice(doc));
        //price.setUrl(getProductUrl(doc));



        List<Price> prices = new ArrayList();
        prices.add(price);
        //product.setPrices(prices);

        return product;
    }

    public static void main(String[] args) {
        PrismaScraper prismaScraper = new PrismaScraper();
        System.out.println(prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/seesamiseemne-urdi-hummus--175-g/4058094300021"));
        System.out.println(prismaScraper.getProductUrlsFromCategory("https://www.prismamarket.ee/products/17097"));
        //prismaScraper.getProductUrlsFromCategory(DocumentManager.getDocument("https://www.prismamarket.ee/products/17636"));
        //prismaScraper.selectStrategy("https://www.prismamarket.ee/products/16974");
        //System.out.println(DocumentManager.getDocument("https://www.prismamarket.ee/products/16972"));
    }
}
