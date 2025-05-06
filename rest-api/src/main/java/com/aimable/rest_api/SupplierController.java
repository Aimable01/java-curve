package com.aimable.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public Page<Supplier> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return supplierService.findAll(pageable);
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
