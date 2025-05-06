package com.aimable.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> findBySupplierName(String username) {
        return supplierRepository.findBySupplierName(username);
    }

    public List<Supplier> findBySupplierAddress(String address) {
        return supplierRepository.findByAddress(address);
    }

    public List<Supplier> all(){
        return supplierRepository.all();
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        return supplierRepository.findById(id)
                .map(existingSupplier -> {
                    existingSupplier.setSupplierName(updatedSupplier.getSupplierName());
                    existingSupplier.setSupplierAddress(updatedSupplier.getSupplierAddress());
                    return supplierRepository.save(existingSupplier);
                })
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
