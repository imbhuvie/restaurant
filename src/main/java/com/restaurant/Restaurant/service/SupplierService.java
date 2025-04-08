package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.entity.Item;
import com.restaurant.Restaurant.entity.Supplier;
import com.restaurant.Restaurant.service.implimentation.SupplierServiceImpl;

import java.util.List;

public interface SupplierService {
    Supplier insertSupplier(Supplier supplier);
    Supplier findSupplierById(int supplierId);
    List<Supplier> findAllSuppliers();
}
