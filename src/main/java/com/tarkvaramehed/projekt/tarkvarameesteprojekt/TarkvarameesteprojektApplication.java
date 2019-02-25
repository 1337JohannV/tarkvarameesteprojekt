package com.tarkvaramehed.projekt.tarkvarameesteprojekt;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import scraper.selver.SelverScraper;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class TarkvarameesteprojektApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarkvarameesteprojektApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(ProductService productService) {
	    return (args) -> {
            SelverScraper selverScraper = new SelverScraper();
            HashMap<Category, List<Product>> sampleData = selverScraper.getSampleData();
            for (Category category : sampleData.keySet()) {
                productService.addAll(sampleData.get(category));
            }
        };
    }

}

