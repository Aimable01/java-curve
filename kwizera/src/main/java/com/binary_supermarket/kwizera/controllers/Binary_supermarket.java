package com.binary_supermarket.kwizera.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Binary_supermarket {

    @GetMapping()
    public String hello() {
        return "Welcome to KALIM's Binary Supermarket management system";
    }
}
