package com.tarkvaramehed.projekt.tarkvarameesteprojekt;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductRepository;
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
                sampleData.get(category).forEach(p -> p.setCategory(category));
            }

            System.out.println("saving all into database");
            productService.addAll(sampleData.get(Category.LIHA_JA_KALA));

            System.out.println("finding by category");
            productService.getProductsByCategory(Category.LIHA_JA_KALA).forEach(p -> System.out.println(p));
        };
    }

}

