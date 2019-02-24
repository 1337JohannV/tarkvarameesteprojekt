package com.tarkvaramehed.projekt.tarkvarameesteprojekt.dao;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;

import java.util.List;

public interface ProductDao {

    Product getById(Long id);

    List<Product> getAll();

    List<Product> getProductsByCategory();

    void insert(Product product);

}
