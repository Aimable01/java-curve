package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.models.CartItem;
import com.binary_supermarket.kwizera.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestParam String customerEmail) {
        return ResponseEntity.ok(cartService.getCartItems(customerEmail));
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @RequestParam String customerEmail,
            @RequestParam String productCode,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(customerEmail, productCode, quantity));
    }
}
