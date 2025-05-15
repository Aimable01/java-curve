package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.models.Purchase;
import com.binary_supermarket.kwizera.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/checkout")
    public ResponseEntity<List<Purchase>> checkout(@RequestParam String customerEmail) {
        return ResponseEntity.ok(purchaseService.checkout(customerEmail));
    }
}
