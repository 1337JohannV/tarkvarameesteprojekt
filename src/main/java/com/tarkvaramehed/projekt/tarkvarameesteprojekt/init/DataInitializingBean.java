package com.tarkvaramehed.projekt.tarkvarameesteprojekt.init;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scraper.Demo;
import scraper.prisma.PrismaScraper;
import scraper.selver.SelverScraper;

import java.util.HashMap;
import java.util.List;


@Component
public class DataInitializingBean implements InitializingBean {

    @Autowired
    ProductService productService;

    @Override
    public void afterPropertiesSet() {
        SelverScraper selverScraper = new SelverScraper();
        Demo demo = new Demo();

        HashMap<Category, List<Product>> sampleData = selverScraper.getSampleData();
        for (Category category : sampleData.keySet()) {
            sampleData.get(category).forEach(p -> p.setCategory(category));
            productService.addAll(sampleData.get(category));
        }
        productService.addAll(demo.getDemoData(Category.PUU_JA_KOOGIVILJAD));
    }
}
