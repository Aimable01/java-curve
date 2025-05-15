package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.models.Quantity;
import com.binary_supermarket.kwizera.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.registerProduct(product));
    }

    @PostMapping("/{productCode}/quantity")
    public ResponseEntity<Quantity> addQuantity(
            @PathVariable String productCode,
            @RequestParam int quantity,
            @RequestParam String operation) {
        return ResponseEntity.ok(productService.addQuantity(productCode, quantity, operation));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
