package com.binary_supermarket.kwizera.repository;

import com.binary_supermarket.kwizera.models.Cart;
import com.binary_supermarket.kwizera.models.CartItem;
import com.binary_supermarket.kwizera.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
