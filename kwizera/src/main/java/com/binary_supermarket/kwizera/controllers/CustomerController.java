package com.binary_supermarket.kwizera.controllers;

import com.binary_supermarket.kwizera.dto.LoginDTO;
import com.binary_supermarket.kwizera.models.Customer;
import com.binary_supermarket.kwizera.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(customerService.authenticate(request.email(), request.password()));
    }
}
