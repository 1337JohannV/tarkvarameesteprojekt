package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.main.exception.ScraperException;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Scraper {

    Product getProductFromPage(String url) throws ScraperException;

    List<String> getCategoryLinks(Category category) throws ScraperException;

    Store getStore();

}