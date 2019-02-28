package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;

import java.util.List;

public interface Scraper {

    List<Product> getProducts();

}
