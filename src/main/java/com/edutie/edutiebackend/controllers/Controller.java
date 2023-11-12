package com.edutie.edutiebackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
    @GetMapping("/customers")
    public String customers() {
        return "Hello Customers!";
    }


}
