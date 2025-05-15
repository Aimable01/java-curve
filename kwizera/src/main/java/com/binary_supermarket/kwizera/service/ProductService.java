package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.models.Quantity;
import com.binary_supermarket.kwizera.repository.ProductRepository;
import com.binary_supermarket.kwizera.repository.QuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final QuantityRepository quantityRepository;

    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }

    public Quantity addQuantity(String productCode, int quantity, String operation) {
        Product product = productRepository.findByCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Quantity qty = new Quantity();
        qty.setProduct(product);
        qty.setQuantity(quantity);
        qty.setOperation(operation);

        return quantityRepository.save(qty);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
