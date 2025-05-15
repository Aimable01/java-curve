package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    Product RegisterProduct(@RequestBody Product product){
        return this.productService.registerProduct(product);
    }

    @GetMapping()
    List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }
}
