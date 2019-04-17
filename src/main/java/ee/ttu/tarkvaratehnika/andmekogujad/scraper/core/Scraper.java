package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;

import java.util.List;

public interface Scraper {

    Product getProductFromPage(String url) throws ScraperException;

    List<String> getCategoryLinks(Category category) throws ScraperException;

    Store getStore();


    //@Deprecated
    //List<Product> getProducts();

    //@Deprecated
    //List<Product> getDemoData(Category category);
}
