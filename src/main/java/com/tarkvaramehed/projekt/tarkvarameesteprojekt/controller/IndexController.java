package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.data.ProductService;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class IndexController {

    @RequestMapping("/")
    public String displayIndex() {
        return "index";
    }

    @CrossOrigin
    @RequestMapping("/Product")
    public String getProducts() {
        System.out.println("Its here!");
        return "Product";
    }


}
