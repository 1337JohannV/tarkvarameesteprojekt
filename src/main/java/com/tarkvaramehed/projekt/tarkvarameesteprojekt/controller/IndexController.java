package com.tarkvaramehed.projekt.tarkvarameesteprojekt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
