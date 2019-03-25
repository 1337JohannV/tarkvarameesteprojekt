package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.search.ProductSearch;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.repository.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSearch productSearch;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping("/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") Category category) {
        return productService.findProductsByCategory(category);
    }

    @RequestMapping(
            path = "/{page}/{size}/{orderBy}/{direction}",
            method = RequestMethod.GET)
    public List<Product> getAll(@PathVariable int page,
                                @PathVariable int size, @PathVariable String direction,
                                @PathVariable String orderBy) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.toLowerCase().equals("desc")) {
            dir = Sort.Direction.DESC;
        }
        return productService.findAll(page, size, dir, orderBy);
    }

    @RequestMapping(
            path = "/{category}/{page}/{size}/{orderBy}/{direction}",
            method = RequestMethod.GET)
    public List<Product> getProductsByCategory(@PathVariable Category category, @PathVariable int page,
                                               @PathVariable int size, @PathVariable String direction,
                                               @PathVariable String orderBy) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.toLowerCase().equals("desc")) {
            dir = Sort.Direction.DESC;
        }
        return productService.findByCategory(category, page, size, dir, orderBy);
    }

    @RequestMapping(
            path = "/search/{searchQuery}",
            method = RequestMethod.GET)
    public List<Product> searchProducts(@PathVariable String searchQuery) {
        return productSearch.search(searchQuery);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public String queryMethod(@RequestParam Map<String, String> customQuery) throws HttpClientErrorException.BadRequest {

        System.out.println("customQuery = brand " + customQuery.containsKey("brand"));
        System.out.println("customQuery = limit " + customQuery.containsKey("limit"));
        System.out.println("customQuery = price " + customQuery.containsKey("price"));
        System.out.println("customQuery = other " + customQuery.containsKey("other"));
        System.out.println("customQuery = sort " + customQuery.containsKey("sort"));

        return customQuery.toString();
    }
}