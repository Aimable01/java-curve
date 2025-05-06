package com.aimable.rest_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    public Optional<Supplier> findBySupplierName(String username);

    @Query("select s from Supplier s where s.supplierAddress=:address")
    List<Supplier> findByAddress(@Param("address") String address);
}
