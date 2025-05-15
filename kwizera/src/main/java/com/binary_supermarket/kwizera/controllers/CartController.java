package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.dto.AddToCartRequestDTO;
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

    @GetMapping("/{email}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable("email") String customerEmail) {
        return ResponseEntity.ok(cartService.getCartItems(customerEmail));
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody AddToCartRequestDTO request) {
        return ResponseEntity.ok(
                cartService.addToCart(
                        request.customerEmail(),
                        request.productCode(),
                        request.quantity()
                )
        );
    }
}
