package com.binary_supermarket.kwizera.repository;

import com.binary_supermarket.kwizera.models.Cart;
import com.binary_supermarket.kwizera.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerAndActiveTrue(Customer customer);
}
