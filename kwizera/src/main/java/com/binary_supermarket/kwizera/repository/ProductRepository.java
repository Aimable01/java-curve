package com.binary_supermarket.kwizera.repository;

import com.binary_supermarket.kwizera.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
