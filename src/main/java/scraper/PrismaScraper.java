package scraper;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.model.Price;
import scraper.model.Product;


public class PrismaScraper {


    public void example(Document doc) {
        Elements products = doc.select("li.item");
        for (Element product : products) {
            System.out.println(product.attr("data-ean"));
            System.out.println(product.selectFirst("a.js-link-item").attr("href"));
        }
    }

    public Product getProductDetails(String url) {

        Document doc = DocumentManager.getDocument(url);

        String producer = doc.getElementById("product-subname").text();

        String productName = doc.getElementById("product-name").text();

        //String imgUrl = doc.getElementById("data-zoom-image").text();
        String ean = doc.select("span[itemprop = sku]").first().text();

        double priceAmount = Double.parseDouble(doc.getElementsByClass("whole-number").first().text()
                + "." + doc.getElementsByClass("decimal").first().text());
        String quantity = doc.getElementsByClass("js-quantity").first().text();


        Product product = new Product();
        Price price = new Price();

        product.setProducer(producer);
        product.setName(productName);
        product.setEan(ean);


        price.setCurrency("€");
        price.setStore("Prisma");
        price.setAmount(priceAmount);
        price.setQuantity(quantity);


        System.out.println(doc);
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println(producer);
        System.out.println(productName);
        System.out.println(ean);
        System.out.println(priceAmount);
        // System.out.println(quantity);
        //System.out.println(imgUrl);
        System.out.println(quantity);


        return null;
    }

    public static void main(String[] args) {
        PrismaScraper prismaScraper = new PrismaScraper();
        prismaScraper.getProductDetails("https://www.prismamarket.ee/entry/naiste-polvikud-cale-2-paari-20-den-visone/8000577544527");
        // prismaScraper.example(DocumentManager.getDocument("https://www.prismamarket.ee/products/19273"));
    }
}
