package scraper.maxima;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.*;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Unit;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import scraper.Scraper;
import scraper.util.DocumentManager;
import scraper.util.RegexMatcher;

import java.util.ArrayList;
import java.util.List;


public class MaximaScraper implements Scraper {

    private DocumentManager documentManager = new DocumentManager();


    @Override
    public List<Product> getProducts() {
        return null;
    }


    @Override
    public List<Product> getDemoData(Category category) {
        return null;
    }

    @Override
    public Product getProductFromPage(String url) {
        Document doc = documentManager.getDocument(url);
        return createProduct(doc);
    }

    private String getName(Document doc) {

        return doc.getElementsByClass("b-product-info--title").get(0).text();

    }

    private String getOrigin(Document doc) {
        return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split(" ", 3)[1];
    }

    private String getProducer(Document doc) {
        if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Kaubamärk:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Kaubamärk:")[1].split("Tarnija kontaktid:")[0];
        } else if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija kontaktid:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Tarnija kontaktid:")[1].split("Tarnija")[0];
        } else {
            return "Producer Undefined";
        }
    }

    private String getImgUrl(Document doc) {

        return doc.getElementsByClass("b-carousel--slide").get(0).child(0).absUrl("src");

    }

    private String getProductUrl(Document doc) {
        return doc.location();
    }


    private Quantity getProductQuantity(Document doc) {

        String name = getName(doc);

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

            // GETS QUANTITY FROM NAME
            return RegexMatcher.extractQuantity(getName(doc).toLowerCase());
        }

    }

    private ProductPrice getPrice(Document doc) {

        ProductPrice pp = new ProductPrice();

        pp.setStore(Store.MAXIMA);
        pp.setUrl(getProductUrl(doc));

        Element el = doc.getElementsByClass("b-product-prices-block").get(0);

        String unitPrice = doc.getElementsByClass("b-product-price--extra").get(0).text();


        UnitPrice unit = new UnitPrice();
        unit.setCurrency(Currency.EUR);
        if (unitPrice.contains("Taara")) {
            List<Element> elements = doc.getElementsByClass("b-product-price--extra").get(0).children();
            String unitPriceDrinks = elements.get(elements.size()-1).text();
            unit.setAmount(Double.parseDouble(unitPriceDrinks.split("€")[1].split("/")[0]));
            unit.setPerUnit(RegexMatcher.matchUnit(unitPriceDrinks.split("/")[1]));
        } else {
        unit.setAmount(Double.parseDouble(unitPrice.split("€")[1].split("/")[0]));
        unit.setPerUnit(RegexMatcher.matchUnit(unitPrice.split("/")[1]));}

        pp.setUnitPrice(unit);

        //Checks if product has an offer.

        if (el.toString().contains("b-product-crossed-out-price")) {

            Price specialPrice = new Price();
            specialPrice.setCurrency(Currency.EUR);
            specialPrice.setAmount(Double.parseDouble(el.getElementsByClass("b-product-price-current-number").get(0).text().split("€")[1]));
            pp.setSpecialPrice(specialPrice);

            Price reg = new Price();
            reg.setCurrency(Currency.EUR);
            reg.setAmount(Double.parseDouble(el.getElementsByClass("b-product-crossed-out-price").get(0).text().split("€")[1]));
            pp.setRegularPrice(reg);

        } else {
            Price regular = new Price();
            regular.setCurrency(Currency.EUR);
            regular.setAmount(Double.parseDouble(el.getElementsByClass("b-product-price-current-number").get(0).text().split("€")[1]));
            pp.setRegularPrice(regular);

        }

        return pp;


    }

    private List<String> getProductUrlsFromPage(String url) {

        //Gets the amount of pages for the category.

        String baseUrl = "https://www.barbora.ee";

        Document doc = documentManager.getDocument(url);

        int pages = 0;
        for (Element n :doc.getElementsByClass("pagination").get(0).children()) {
            if(Character.isDigit(n.text().charAt(0))){
                if(pages < Integer.parseInt(n.text())) {
                    pages = Integer.parseInt(n.text());
                }
            }
        }

        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            Document doc2 = documentManager.getDocument(url + "?page=" + i);

            for (Element n: doc2.getElementsByClass("b-product-title b-product-title--desktop b-link--product-info")) {
                urls.add(baseUrl+ n.attr("href"));

            }
        }


        System.out.println(urls.size());

        return urls;


    }

    private Product createProduct(Document doc) {

        //NO EAN available on site.

        Product product = new Product();
        product.setProducer(getProducer(doc));
        product.setOrigin(getOrigin(doc));
        product.setName(getName(doc));
        product.setImgUrl(getImgUrl(doc));
        product.setQuantity(getProductQuantity(doc));
        List<ProductPrice> prices = new ArrayList<>();
        prices.add(getPrice(doc));
        product.setProductPrices(prices);
        return product;
    }


    public static void main(String[] args) {
        MaximaScraper scraper = new MaximaScraper();
        DocumentManager documentManager = new DocumentManager();

        Document doc = documentManager.getDocument("https://www.barbora.ee/toode/alk-vaba-olu-saku-go-0-5-l-prk");


        System.out.println(scraper.createProduct(doc));

        Document doc2 = documentManager.getDocument("https://www.barbora.ee/leivad-saiad-kondiitritooted");
        //System.out.println(scraper.getProductUrlsFromPage("https://www.barbora.ee/leivad-saiad-kondiitritooted"));

        //"https://www.barbora.ee/toode/alk-vaba-olu-saku-go-0-5-l-prk"
    }
}
