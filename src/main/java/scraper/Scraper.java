package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.HashMap;
import java.util.List;

public interface Scraper {

    HashMap<Category, List<Product>> scrapeCategories();
}
