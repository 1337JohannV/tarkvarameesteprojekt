package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.repository;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.ProductPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Store;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findProductsByCategory(Category category);

    List<Product> findAll(int page, int size, Sort.Direction direction, String orderBy);

    List<Product> findByCategory(Category category, int page, int size, Sort.Direction direction, String orderBy);

    List<Product> findByStore(Store store, int page, int size);

    void save(Product product);

    void saveAll(Iterable<Product> products);

}
