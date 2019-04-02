package com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.repository;


import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
@Transactional
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAll(Iterable<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll(int page, int size, Sort.Direction direction, String orderBy) {
        return productRepository.findAll(PageRequest.of(page, size, direction, orderBy)).getContent();
    }

    @Override
    public List<Product> findByCategory(Category category, int page, int size,
                                        Sort.Direction direction, String orderBy) {
        return productRepository.findAllByCategory(category,
                PageRequest.of(page, size, direction, orderBy));
    }
}
