package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product registerProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }
}
