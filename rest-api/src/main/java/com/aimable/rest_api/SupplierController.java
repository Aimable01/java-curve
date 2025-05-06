package com.aimable.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }

    @PostMapping()
    public Supplier save(@RequestBody Supplier supplier) {
        return this.supplierService.createSupplier(supplier);
    }

    @GetMapping("/id/{id}")
    public Optional<Supplier> findById(@PathVariable Long id) {
        return this.supplierService.findById(id);
    }

    @GetMapping("/supplierName/{name}")
    public Optional<Supplier> findBySupplierName(@PathVariable String name) {
        return this.supplierService.findBySupplierName(name);
    }
}
