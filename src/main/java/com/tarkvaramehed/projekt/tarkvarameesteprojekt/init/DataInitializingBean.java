package com.tarkvaramehed.projekt.tarkvarameesteprojekt.init;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.repository.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scraper.ScraperMaster;

import java.util.List;

@Component
public class DataInitializingBean implements InitializingBean {

    @Autowired
    ProductService productService;

    @Override
    public void afterPropertiesSet() {
        if (productService.findAll().size() == 0) {
            System.out.println("DB is empty");
            ScraperMaster sm = new ScraperMaster();
            List<Product> products = sm.scrapeProducts();
            productService.saveAll(products);
        }
        System.out.println(productService.findAll().size());
    }
}
