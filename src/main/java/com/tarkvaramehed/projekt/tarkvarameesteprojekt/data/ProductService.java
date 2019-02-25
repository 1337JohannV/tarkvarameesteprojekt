package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id);

    List<Product> getAll();

    List<Product> getProductsByCategory();

    void add(Product product);

}
