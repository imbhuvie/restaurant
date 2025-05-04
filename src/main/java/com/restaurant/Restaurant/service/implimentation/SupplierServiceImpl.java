package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.MeasurementUnit;
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
    public String generateNextUnitId() {
        Supplier lastUnit = supplierRepository.findTopByOrderBySupplierIdDesc();
        if (lastUnit == null) {
            return "SP01";
        }

        String lastId = lastUnit.getSupplierId(); // e.g., UN05
        int number = Integer.parseInt(lastId.substring(2)); // 05 → 5
        number++;
        return String.format("SP%02d", number); // UN06
    }

    @Override
    public Supplier insertSupplier(Supplier supplier) {
        if(supplier.getSupplierId()==null){
        supplier.setSupplierId(generateNextUnitId());
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findSupplierById(String supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public boolean deleteSupplierById(String id) {
        try{
            supplierRepository.deleteById(id);
            return true;
        }catch (Exception e){
        return false;
        }
    }
}
