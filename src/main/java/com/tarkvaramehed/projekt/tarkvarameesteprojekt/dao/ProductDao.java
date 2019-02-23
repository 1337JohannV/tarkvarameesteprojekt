package com.tarkvaramehed.projekt.tarkvarameesteprojekt.dao;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;

import java.util.List;

public interface ProductDao {

    Product getById();

    List<Product> getAll();

    List<Product> getProductsByCategory();

}
