package com.binary_supermarket.kwizera.service;

import com.binary_supermarket.kwizera.models.Customer;
import com.binary_supermarket.kwizera.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
//        if (customerRepository.existsByEmail(customer.getEmail())) {
//            throw new RuntimeException("Email already registered");
//        }
        return customerRepository.save(customer);
    }

    public Customer authenticate(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> c.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
