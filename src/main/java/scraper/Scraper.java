package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.List;

public interface Scraper {

    List<Product> getProducts();

    List<Product> getDemoData(Category category);

    Product getProductFromPage(String url);

}
