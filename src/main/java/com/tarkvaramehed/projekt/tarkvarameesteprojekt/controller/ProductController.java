package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scraper.Demo;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Demo testScraper;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") Category category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/test")

    public List<Product> getProducts() {
        return testScraper.getDemoData(Category.PUU_JA_KOOGIVILJAD);
    }
}