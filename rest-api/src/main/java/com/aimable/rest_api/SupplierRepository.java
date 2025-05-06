package com.aimable.rest_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    public Optional<Supplier> findBySupplierName(String username);
}
