package scraper.maxima;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Quantity;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Unit;
import org.jsoup.nodes.Document;
import scraper.Scraper;
import scraper.util.DocumentManager;
import scraper.util.RegexMatcher;

import java.util.List;


public class MaximaScraper implements Scraper {

    private DocumentManager documentManager = new DocumentManager();

    @Override
    public List<Product> getDemoData(Category category) {
        return null;
    }

    @Override
    public Product getProductFromPage(String url) {
        return null;
    }

    public static void main(String[] args) {
        MaximaScraper scraper = new MaximaScraper();

        Document doc = scraper.documentManager.getDocument("https://www.barbora.ee/toode/salat-potis-tk");
        //System.out.println(scraper.getName(doc));
        //System.out.println(scraper.getOrigin(doc));
        //System.out.println(scraper.getProducer(doc));
        //System.out.println(scraper.createProduct(doc));
        // System.out.println(scraper.getImgUrl(doc));

        System.out.println(scraper.getProductQuantity(doc));
        System.out.println(RegexMatcher.extractQuantity("Salat potis, tk"));
        System.out.println(RegexMatcher.extractQuantity("Alk.vaba õlu SAKU GO 0.5L, prk"));
        System.out.println(scraper.createProduct(doc));

        //"https://www.barbora.ee/toode/alk-vaba-olu-saku-go-0-5-l-prk"
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    public String getName(Document doc) {

        return doc.getElementsByClass("b-product-info--title").get(0).text();

    }

    public String getOrigin(Document doc) {
        return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split(" ", 3)[1];
    }

    public String getProducer(Document doc) {
        if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Kaubamärk:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Kaubamärk:")[1].split("Tarnija kontaktid:")[0];
        } else if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija kontaktid:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Tarnija kontaktid:")[1].split("Tarnija")[0];
        } else {
            return "Producer Undefined";
        }
    }

    public String getImgUrl(Document doc) {

        return doc.getElementsByClass("b-carousel--slide").get(0).child(0).absUrl("src");

    }

    public String getProductUrl(Document doc) {
        return doc.location();
    }

    public Quantity getProductQuantity(Document doc) {

        String name = getName(doc);
        System.out.println(name);

        Quantity q = new Quantity();

        String[] array = name.toLowerCase().split("");

        for (int i = 1; i < array.length; i++) {

            if (array[i].equals("l") && Character.isDigit(array[i - 1].charAt(0))) {
                q.setUnit(Unit.ML);
                break;
            } else if (array[i].equals("l") && array[i - 1].equals(" ") && Character.isDigit(array[i - 2].charAt(0))) {
                q.setUnit(Unit.ML);
                break;
            } else if (array[i].equals("l") && array[i - 1].equals("m") && Character.isDigit(array[i - 2].charAt(0))) {
                q.setUnit(Unit.ML);
                break;
            } else if (array[i].equals("l") && array[i - 1].equals("m") && array[i - 2].equals(" ") && Character.isDigit(array[i - 3].charAt(0))) {
                q.setUnit(Unit.ML);
                break;
            } else {
                q.setUnit(Unit.G);
            }


        }

        if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Netokogus (g/ml):")
                && doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija kontaktid:")) {

            q.setValue(Double.parseDouble(doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0)
                    .text().split("Netokogus")[1].split(":")[1].split("Tarnija kontaktid")[0].split("Kaubamärk")[0]));

            return q;

        } else if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Netokogus (g/ml):") &&
                doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija:")) {
            q.setValue(Double.parseDouble(doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Netokogus")[1].split(":")[1].split("Tarnija")[0]));
            return q;
        } else {
            // GETS QUANTITY AMOUNT FROM NAME
            return RegexMatcher.extractQuantity(getName(doc).toLowerCase());
        }

    }

    public Product createProduct(Document doc) {

        //NO EAN available on site.


        Product product = new Product();
        Price p = new Price();
        ProductPrice pp = new ProductPrice();
        p.setCurrency(Currency.EUR);

        pp.setStore(Store.MAXIMA);
        pp.setUrl(getProductUrl(doc));


        product.setProducer(getProducer(doc));
        product.setOrigin(getOrigin(doc));
        product.setName(getName(doc));
        product.setImgUrl(getImgUrl(doc));
        product.setQuantity(getProductQuantity(doc));
        return product;
    }
}
