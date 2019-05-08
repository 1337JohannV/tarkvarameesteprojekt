package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.Scraper;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.DocumentManager;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util.RegexMatcher;
import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

@Component
public class MaximaScraper implements Scraper {

    @Override
    public List<String> getCategoryLinks(Category category) throws ScraperException {
        try {
            List<String> baseLinks = MaximaUrlManager.getSubCatUrls(category);
            List<String> links = new ArrayList<>();
            assert baseLinks != null;
            for (String s : baseLinks) {
                for (String link : getProductUrlsFromPage(s)) {
                    links.add(link);
                }
            }
            return links;
        } catch (Exception e) {
            throw new ScraperException(getStore(), null, "Exception while getting category links", e);
        }
    }

    @Override
    public String toString() {
        return "Scraper: " + Store.MAXIMA;
    }

    @Override
    public Store getStore() {
        return Store.MAXIMA;
    }

    @Override
    public Product getProductFromPage(String url) throws ScraperException {
        try {
            Document doc = DocumentManager.getDocument(url);
            return createProduct(doc);
        } catch (Exception e) {
            throw new ScraperException(getStore(), url, "Exception while scraping product page", e);
        }
    }

    private String getName(Document doc) {
        return doc.getElementsByClass("b-product-info--title").get(0).text();
    }

    private String getOrigin(Document doc) {
        return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split(" ", 3)[1];
    }

    private String getProducer(Document doc) {
        if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Kaubamärk:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Kaubamärk:")[1].split("Tarnija kontaktid:")[0].split("Tarnija")[0];
        } else if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija kontaktid:")) {
            return doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Tarnija kontaktid:")[1].split("Tarnija")[0];
        } else {
            return null;
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
            String amount = doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0)
                    .text().split("Netokogus")[1].split(":")[1].split("Tarnija kontaktid")[0].split("Kaubamärk")[0];

            if (amount.contains(",")) {
                String amount2 = amount.replace(",", ".");
                q.setValue(Double.parseDouble(amount2));
            } else {
                q.setValue(Double.parseDouble(amount));
                return q;
            }
        } else if (doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Netokogus (g/ml):") &&
                doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().contains("Tarnija:")) {
            String amount = doc.getElementsByClass("b-dl-align-left b-product-info--info1").get(0).text().split("Netokogus")[1].split(":")[1].split("Tarnija")[0]
                    .split("Kaubamärk")[0];
            if (amount.contains(",")) {
                String amount2 = amount.replace(",", ".");
                q.setValue(Double.parseDouble(amount2));
            } else if (!amount.contains(",")) {
                q.setValue(Double.parseDouble(amount));
                return q;
            }
        }
        //GETS QUANTITY FROM NAME
        try {
            if (RegexMatcher.extractQuantity(getName(doc).toLowerCase()) != null) {
                return RegexMatcher.extractQuantity(getName(doc).toLowerCase());
            }
        } catch (NumberFormatException e) {
            return new Quantity(1d, getPrice(doc).getUnitPrice().getPerUnit());
        }
        return new Quantity(1d, getPrice(doc).getUnitPrice().getPerUnit());
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
            String unitPriceDrinks = elements.get(elements.size() - 1).text();
            unit.setAmount(Double.parseDouble(unitPriceDrinks.split("€")[1].split("/")[0]));
            unit.setPerUnit(RegexMatcher.matchUnit(unitPriceDrinks.split("/")[1]));
        } else if (unitPrice.contains(" ")) {
            String unitPrice2 = unitPrice.replace(" ", "");
            unit.setAmount(Double.parseDouble(unitPrice2.split("€")[1].split("/")[0]));
            unit.setPerUnit(RegexMatcher.matchUnit(unitPrice2.split("/")[1]));
        } else {
            unit.setAmount(Double.parseDouble(unitPrice.split("€")[1].split("/")[0]));
            unit.setPerUnit(RegexMatcher.matchUnit(unitPrice.split("/")[1]));
        }
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
        Document doc = DocumentManager.getDocument(url);
        int pages = 0;

        for (Element n : doc.getElementsByClass("pagination").get(0).children()) {
            if (Character.isDigit(n.text().charAt(0))) {
                if (pages < Integer.parseInt(n.text())) {
                    pages = Integer.parseInt(n.text());
                }
            }
        }

        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            Document doc2 = DocumentManager.getDocument(url + "?page=" + i);
            for (Element n : doc2.getElementsByClass("b-product-title b-product-title--desktop b-link--product-info")) {
                urls.add(baseUrl + n.attr("href"));
            }
        }
        return urls;
    }

    private Product createProduct(Document doc) {
        //NO EAN available on site.
        if (getProductQuantity(doc) != null) {
            Product product = new Product();
            product.setProducer(getProducer(doc));
            product.setOrigin(getOrigin(doc));
            product.setName(getName(doc));
            product.setImgUrl(getImgUrl(doc));
            List<ProductPrice> prices = new ArrayList<>();
            prices.add(getPrice(doc));
            product.setProductPrices(prices);
            product.setQuantity(getProductQuantity(doc));
            return product;
        } else {
            return null;
        }
    }

}
