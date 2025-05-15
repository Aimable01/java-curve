package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Cart;
import com.binary_supermarket.kwizera.models.CartItem;
import com.binary_supermarket.kwizera.models.Customer;
import com.binary_supermarket.kwizera.models.Product;
import com.binary_supermarket.kwizera.repository.CartItemRepository;
import com.binary_supermarket.kwizera.repository.CartRepository;
import com.binary_supermarket.kwizera.repository.CustomerRepository;
import com.binary_supermarket.kwizera.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public Cart getOrCreateActiveCart(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return cartRepository.findByCustomerAndActiveTrue(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });
    }

    public CartItem addToCart(String customerEmail, String productCode, int quantity) {
        Cart cart = getOrCreateActiveCart(customerEmail);
        Product product = productRepository.findByCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            return cartItemRepository.save(newItem);
        }
    }

    public List<CartItem> getCartItems(String customerEmail) {
        Cart cart = getOrCreateActiveCart(customerEmail);
        return cartItemRepository.findByCart(cart);
    }
}
