package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.List;

public interface ProductService {

    Product getById(Long id);

    List<Product> getAll();

    List<Product> getProductsByCategory(Category category);

    void add(Product product);

    void addAll(Iterable<Product> products);

}
