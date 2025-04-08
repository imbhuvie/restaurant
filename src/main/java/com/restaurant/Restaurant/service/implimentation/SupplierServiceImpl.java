package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.Supplier;
import com.restaurant.Restaurant.repository.SupplierRepository;
import com.restaurant.Restaurant.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Override
    public Supplier insertSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findSupplierById(int supplierId) {
        return null;
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }
}
