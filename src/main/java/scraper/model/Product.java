package scraper.model;

public class Product {

    private String url;
    private String name;
    private String ean;
    private String producer;
    private String origin;
    private Price price;

    public Product(String url, String name, String ean, String producer, String origin, Price price) {
        this.url = url;
        this.name = name;
        this.ean = ean;
        this.producer = producer;
        this.origin = origin;
        this.price = price;
    }

    public Product() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("<product> url: %s || name: %s || ean: %s || price: %s || made by: %s @ %s", url, name, ean, price, producer, origin);
    }

}