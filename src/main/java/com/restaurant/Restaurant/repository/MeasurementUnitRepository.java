package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit,Long> {
    List<MeasurementUnit> findByUnitName(String unitName);
}
