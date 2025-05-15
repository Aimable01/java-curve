package com.binary_supermarket.kwizera.repository;

import com.binary_supermarket.kwizera.models.Customer;
import com.binary_supermarket.kwizera.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByCustomer(Customer customer);
}
