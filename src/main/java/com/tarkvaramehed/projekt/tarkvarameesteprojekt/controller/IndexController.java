package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scraper.selver.SelverScraper;

import java.util.HashMap;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    SelverScraper testScraper;

    @RequestMapping("/")
    public String displayIndex() {
        return "index";
    }

    @CrossOrigin
    @RequestMapping("/Product")
    public HashMap<Category, List<Product>> getProducts() {
        return testScraper.getSampleData();
    }


}
