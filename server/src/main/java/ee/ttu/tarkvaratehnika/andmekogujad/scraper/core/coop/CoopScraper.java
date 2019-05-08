package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.coop;

import ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.Scraper;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Product;

import java.util.List;

public class CoopScraper implements Scraper {

    public static void main(String[] args) {
        CoopScraper cs = new CoopScraper();
    }

    /*
    @Override
    public List<Product> getProducts() {
        return null;
    }
    */

    @Override
    public Product getProductFromPage(String url) {
        return null;
    }

    @Override
    public List<String> getCategoryLinks(Category category) {
        return null;
    }


    @Override
    public Store getStore() {
        return null;
    }

    /*
    @Override
    public List<Product> getDemoData(Category category) {
        return null;
    }
    */


}
