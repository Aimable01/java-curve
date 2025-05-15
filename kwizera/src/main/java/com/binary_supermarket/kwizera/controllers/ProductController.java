package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.models.Quantity;
import com.binary_supermarket.kwizera.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody Product product) {
        try {
            Product registeredProduct = productService.registerProduct(product).getBody();
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredProduct);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/{productCode}/quantity")
    public ResponseEntity<?> addQuantity(
            @PathVariable String productCode,
            @RequestParam int quantity,
            @RequestParam String operation) {
        try {
            Quantity addedQuantity = productService.addQuantity(productCode, quantity, operation).getBody();
            return ResponseEntity.ok(addedQuantity);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = (List<Product>) productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
