package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.MeasurementUnit;
import com.restaurant.Restaurant.repository.MeasurementUnitRepository;
import com.restaurant.Restaurant.service.MeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {
    @Autowired
    MeasurementUnitRepository measurementUnitRepository;
    @Override
    public MeasurementUnit addUnit(MeasurementUnit measurementUnit) {
        return measurementUnitRepository.save(measurementUnit);
    }

    @Override
    public List<MeasurementUnit> allUnits() {
        return measurementUnitRepository.findAll();
    }
}
