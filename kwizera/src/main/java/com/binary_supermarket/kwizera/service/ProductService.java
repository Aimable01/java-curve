package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.models.Quantity;
import com.binary_supermarket.kwizera.repository.ProductRepository;
import com.binary_supermarket.kwizera.repository.QuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final QuantityRepository quantityRepository;

    public ResponseEntity<Product> registerProduct(Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error registering product", e);
        }
    }

    public ResponseEntity<Quantity> addQuantity(String productCode, int quantity, String operation) {
        try {
            Product product = productRepository.findByCode(productCode)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with code: " + productCode));

            Quantity qty = new Quantity();
            qty.setProduct(product);
            qty.setQuantity(quantity);
            qty.setOperation(operation);

            Quantity savedQuantity = quantityRepository.save(qty);
            return ResponseEntity.ok(savedQuantity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding quantity", e);
        }
    }

    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving products", e
            );
        }
    }
}
