package scraper.util;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Quantity;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.ArrayList;
import java.util.List;

public class ProductMerger {

    /** This is done after products are scraped and products with same ean aka same products
     * have to be combined into one
     * @param ean product ean code
     * @param products list of products with same ean
     * @return merged product
     */
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
        //not yet implemented in product because
        //it breaks the system when trying to update the schema or whatnot
        //these values are for sorting later on
        //base weight unit is g/ml/tk and currency is eur
        //product.setBasePrice(convertToBasePrice(getAveragePrice(product)));
        //product.setBaseWeight(convertToBaseWeight(product.getQuantity()));
        return product;
    }

    private Category getBestCategory(List<Product> products) {
        //TODO COMPARE CATEGORIES AND RETURN BEST CANDIDATE
        return null;
    }

    private String getBestName(List<Product> products) {
        //TODO COMPARE NAMES AND RETURN BEST CANDIDATE
        return "";
    }

    private Quantity getBestQuantity(List<Product> products){
        //TODO COMPARE QUANTITIES AND RETURN BEST CANDIDATE
        return new Quantity();
    }

    private String getBestProducer(List<Product> products) {
        //TODO COMPARE PRODUCERS AND RETURN BEST CANDIDATE
        return "";
    }

    private String getBestOrigin(List<Product> products) {
        //TODO COMPARE ORIGINS AND RETURN BEST CANDIDATE
        return "";
    }

    private String getBestImgUrl(List<Product> products){
        //TODO COMPARE IMG URLS AND RETURN BEST CANDIDATE
        return "";
    }

    // THIS IS HAS TO BE DONE AFTER PRICES ARE MERGED
    private Price getAveragePrice(Product product) {
        //TODO COMPARE PRICES AND RETURN AVERAGE

        return new Price();
    }

    //base weight is g / ml / tk
    private double convertToBaseWeight(Quantity quantity){
        double value = quantity.getValue();
        switch (quantity.getUnit()) {
            case KG: return value * 1000;
//            case TK: return value;
//            case G: return value;
//            case ML: return value;
            case L: return value * 1000;
            case CL: return value * 10;
        }
        return value;
    }

    //base currency is eur
    private double convertToBasePrice(Price price) {
        double amount = price.getAmount();
        switch (price.getCurrency()) {
            case EUR: return amount;
            case USD: return amount * 0.88;
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
}
