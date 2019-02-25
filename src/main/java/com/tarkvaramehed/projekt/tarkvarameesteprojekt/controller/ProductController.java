package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") Category category) {
        return productService.getProductsByCategory(category);
    }
}