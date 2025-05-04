package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,String> {
    Supplier findTopByOrderBySupplierIdDesc();

}
