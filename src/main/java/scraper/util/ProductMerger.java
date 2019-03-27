package scraper.util;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Quantity;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductMerger {

    public Product mergeProducts(String ean, List<Product> products) {
        Product product = new Product();
        product.setEan(ean);
        product.setName(getBestName(products));
        product.setCategory(getBestCategory(products));
        product.setProductPrices(mergeProductPrices(products));
        product.setQuantity(getBestQuantity(products));
        product.setProducer(getBestProducer(products));
        product.setOrigin(getBestOrigin(products));
        product.setImgUrl(getBestImgUrl(products));
        //product.setBasePrice(convertToBasePrice(getMinPrice(product)));
        //product.setBaseWeight(convertToBaseWeight(product.getQuantity()));
        return product;
    }

    private Category getBestCategory(List<Product> products) {
        //Finds most common Category
        return products.stream().filter(p -> Objects.nonNull(p.getCategory()))
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

    }

    private String getBestName(List<Product> products) {
        return products.stream().filter(p -> (p.getName() != null))
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("NAME NOT DEFINED");
    }

    private Quantity getBestQuantity(List<Product> products) {
        return products.stream().filter(p -> p.getQuantity() != null).map(Product::getQuantity).findFirst().orElse(new Quantity());
    }

    private String getBestProducer(List<Product> products) {
        return products.stream().filter(p -> p.getName() != null).map(Product::getProducer).findFirst().orElse("NO PRODUCER DEFINED");

    }

    private String getBestOrigin(List<Product> products) {
        return products.stream().filter(p -> p.getOrigin() != null).map(Product::getOrigin).findFirst().orElse("NO ORIGIN DEFINED");

    }

    private String getBestImgUrl(List<Product> products) {
        return products.stream().filter(p -> p.getImgUrl() != null).map(Product::getImgUrl).findFirst().orElse("NO URL DEFINED");

    }

    // THIS IS HAS TO BE DONE AFTER PRICES ARE MERGED
    private Price getMinPrice(Product product) {

        Price priceToSet = new Price();
        priceToSet.setCurrency(Currency.EUR);
        double amount = product.getProductPrices().stream().filter(p->p.getRegularPrice().getAmount() != null).map(p -> p.getRegularPrice().getAmount()).min(Double::compare).orElse((double) 0);
        priceToSet.setAmount(amount);
        return priceToSet;
    }

    //base weight is g / ml / tk
    private double convertToBaseWeight(Quantity quantity) {
        double value = quantity.getValue();
        switch (quantity.getUnit()) {
            case KG:
                return value * 1000;
//            case TK: return value;
//            case G: return value;
//            case ML: return value;
            case L:
                return value * 1000;
            case CL:
                return value * 10;
        }
        return value;
    }

    //base currency is eur
    private double convertToBasePrice(Price price) {
        double amount = price.getAmount();
        switch (price.getCurrency()) {
            case EUR:
                return amount;
            case USD:
                return amount * 0.88;
        }
        return amount;
    }

    private List<ProductPrice> mergeProductPrices(List<Product> products) {
        List<ProductPrice> productPrices = new ArrayList<>();
        for (Product product : products) {
            productPrices.addAll(product.getProductPrices());
        }
        return productPrices;
    }

    public static void main(String[] args) {
        ProductMerger merger = new ProductMerger();
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();
        p1.setCategory(Category.JOOGID);

        p3.setCategory(Category.KUIVAINED_HOIDISED);
        p4.setCategory(Category.KUIVAINED_HOIDISED);
        p1.setName("juust, farmi");
        p4.setName("juust, xd");
        p1.setProducer("FARMI");
        p2.setProducer("ASS");
        p3.setProducer("ASS");
        //p1.setOrigin("Weebcity");
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        ProductPrice price1 = new ProductPrice();
        ProductPrice price2 = new ProductPrice();
        Price p11 = new Price();
        Price p12 = new Price();

        p11.setCurrency(Currency.EUR);
        p12.setCurrency(Currency.EUR);
        p11.setAmount((double)20);
        p12.setAmount((double) 10);
        price1.setRegularPrice(p11);
        price2.setRegularPrice(p12);


        List<ProductPrice> priceslist = new ArrayList<>();

        priceslist.add(price1);
        priceslist.add(price2);

        price1.setRegularPrice(p11);
        p1.setProductPrices(priceslist);

        System.out.println(merger.getMinPrice(p1));


    }
}
