package com.tarkvaramehed.projekt.tarkvarameesteprojekt.init;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scraper.selver.SelverScraper;

import java.util.HashMap;
import java.util.List;


@Component
public class DataInitializingBean implements InitializingBean {

    @Autowired
    ProductService productService;

    @Override
    public void afterPropertiesSet() {
        if (productService.getAll().size() == 0) {
            System.out.println("DB is empty");
            SelverScraper selverScraper = new SelverScraper();
            HashMap<Category, List<Product>> sampleData = selverScraper.getSampleData();
            for (Category category : sampleData.keySet()) {
            sampleData.get(category).forEach(p -> p.setCategory(category));
            productService.addAll(sampleData.get(category));
            }
        }
        System.out.println(productService.getAll().size());
    }
}
