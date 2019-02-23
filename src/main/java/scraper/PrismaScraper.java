package scraper;


import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class PrismaScraper {


    public void example(Document doc) {
        Elements products = doc.select("li.item");
        for (Element product : products) {
            System.out.println(product.attr("data-ean"));
            System.out.println(product.selectFirst("a.js-link-item").attr("href"));
        }
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


        price.setCurrency("€");
        price.setStore("Prisma");
        price.setAmount(getPrice(doc));
        price.setQuantity(quantity);
        price.setUnitPrice(getUnitPrice(doc));
        price.setUrl(getProductUrl(doc));



        List<Price> prices = new ArrayList();
        prices.add(price);
        product.setPrices(prices);

        return product;
    }

    public static void main(String[] args) {
        PrismaScraper prismaScraper = new PrismaScraper();
        System.out.println(prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/pipraveski-17-cm/6412988352042"));
        // prismaScraper.example(DocumentManager.getDocument("https://www.prismamarket.ee/products/19273"));
    }
}
