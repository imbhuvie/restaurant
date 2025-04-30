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
    public String addUnit(MeasurementUnit measurementUnit) {
        try {
            List<MeasurementUnit> existUnit = measurementUnitRepository.findByUnitName(measurementUnit.getUnitName());
            if (existUnit == null) {
                measurementUnitRepository.save(measurementUnit);
                return "added";
            } else {
                boolean isFound = true;
                for (MeasurementUnit unit : existUnit) {
                    if ((unit.getSymbol().equals(measurementUnit.getSymbol()))) {
                        return "exists";
                    } else {
                        isFound = false;
                    }
                }
                if (!isFound) {
                    measurementUnitRepository.save(measurementUnit);
                    return "added";
                }
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public List<MeasurementUnit> allUnits() {
        return measurementUnitRepository.findAll();
    }
}
