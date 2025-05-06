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

    @GetMapping("/name/{name}")
    public Optional<Supplier> findBySupplierName(@PathVariable String name) {
        return this.supplierService.findBySupplierName(name);
    }

    @GetMapping("/address/{address}")
    public List<Supplier> findByAddress(@PathVariable String address) {
        return this.supplierService.findBySupplierAddress(address);
    }

    @GetMapping("/all")
    public List<Supplier> all(){
        return this.supplierService.all();
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Long id, @RequestBody Supplier supplier) {
        return this.supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        this.supplierService.deleteSupplier(id);
        return "Deleted Supplier with id: " + id;
    }
}
