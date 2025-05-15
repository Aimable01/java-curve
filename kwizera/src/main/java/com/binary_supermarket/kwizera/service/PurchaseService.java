package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Cart;
import com.binary_supermarket.kwizera.models.CartItem;
import com.binary_supermarket.kwizera.models.Customer;
import com.binary_supermarket.kwizera.models.Purchase;
import com.binary_supermarket.kwizera.repository.CartRepository;
import com.binary_supermarket.kwizera.repository.CustomerRepository;
import com.binary_supermarket.kwizera.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CartService cartService;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Transactional
    public List<Purchase> checkout(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Cart cart = cartService.getOrCreateActiveCart(customerEmail);
        List<CartItem> cartItems = cart.getItems();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        List<Purchase> purchases = new ArrayList<>();

        for (CartItem item : cartItems) {
            Purchase purchase = new Purchase();
            purchase.setCustomer(customer);
            purchase.setProduct(item.getProduct());
            purchase.setQuantity(item.getQuantity());
            purchase.setTotal(item.getProduct().getPrice() * item.getQuantity());
            purchases.add(purchaseRepository.save(purchase));
        }

        cart.setActive(false);
        cartRepository.save(cart);

        return purchases;
    }
}
