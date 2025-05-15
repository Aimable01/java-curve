package com.binary_supermarket.kwizera.dto;

public record AddToCartRequestDTO(
        String customerEmail,
        String productCode,
        int quantity
) {}
