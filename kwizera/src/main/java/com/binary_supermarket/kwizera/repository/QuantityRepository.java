package com.binary_supermarket.kwizera.repository;

import com.binary_supermarket.kwizera.models.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    List<Quantity> findByProductCode(String productCode);
}
