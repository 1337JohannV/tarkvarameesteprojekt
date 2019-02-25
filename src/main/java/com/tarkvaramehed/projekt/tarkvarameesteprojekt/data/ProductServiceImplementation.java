package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data;


import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = new ArrayList<>();
        productRepository.findProductByCategory(category).forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void addAll(Iterable<Product> products) {
        productRepository.saveAll(products);
    }
}
