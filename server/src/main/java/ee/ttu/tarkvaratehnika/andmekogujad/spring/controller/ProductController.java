package ee.ttu.tarkvaratehnika.andmekogujad.spring.controller;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.BasketService;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Store;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.model.Product;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.ProductService;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.product.service.search.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSearch productSearch;

    @Autowired
    BasketService basketService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @RequestMapping(path = "/store/{store}", method = RequestMethod.GET)
    public List<Product> getProductsByStore(@PathVariable("store") Store store) {
        return productService.findByStore(store);
    }

    @RequestMapping(path = "/category/{category}", method = RequestMethod.GET)
    public List<Product> getProductByCategory(@PathVariable("category") Category category) {
        return productService.findProductsByCategory(category);
    }

    @RequestMapping(path = "/{page}/{size}/{orderBy}/{direction}", method = RequestMethod.GET)
    public List<Product> getAllProductsPageable(@PathVariable int page, @PathVariable int size,
                                                @PathVariable String direction, @PathVariable String orderBy) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.toLowerCase().equals("desc")) {
            dir = Sort.Direction.DESC;
        }
        return productService.findAll(page, size, dir, orderBy);
    }

    @RequestMapping(path = "/{category}/{page}/{size}/{orderBy}/{direction}", method = RequestMethod.GET)
    public List<Product> getProductsByCategoryPageable(@PathVariable Category category, @PathVariable int page,
                                                       @PathVariable int size, @PathVariable String direction, @PathVariable String orderBy) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.toLowerCase().equals("desc")) {
            dir = Sort.Direction.DESC;
        }
        return productService.findByCategory(category, page, size, dir, orderBy);
    }

    @RequestMapping(path = "/search/{searchQuery}", method = RequestMethod.GET)
    public List<Product> productSearch(@PathVariable String searchQuery) {
        return productSearch.search(searchQuery);
    }

    @RequestMapping(path = "/search/{searchQuery}/{page}/{size}/{direction}", method = RequestMethod.GET)
    public List<Product> productSearchPageable(@PathVariable String searchQuery, @PathVariable int page,
                                               @PathVariable int size, @PathVariable String direction) {
        int dir = 0;
        if (direction.toLowerCase().equals("desc")) {
            dir = 1;
        }
        return productSearch.searchPages(searchQuery, page, size, dir);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public String queryMethod(@RequestParam Map<String, String> customQuery)
            throws HttpClientErrorException.BadRequest {

        System.out.println("customQuery = brand " + customQuery.containsKey("brand"));
        System.out.println("customQuery = limit " + customQuery.containsKey("limit"));
        System.out.println("customQuery = price " + customQuery.containsKey("price"));
        System.out.println("customQuery = other " + customQuery.containsKey("other"));
        System.out.println("customQuery = sort " + customQuery.containsKey("sort"));

        return customQuery.toString();
    }
    @RequestMapping(method = RequestMethod.POST, path = "/ostukorv")
    public HashMap<Store, Double> getBestStore(@RequestBody List<Product> products) {

        return basketService.getShopAndPriceFromList(products);

    }

}