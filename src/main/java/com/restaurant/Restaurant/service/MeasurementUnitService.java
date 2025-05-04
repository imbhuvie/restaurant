package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.entity.MeasurementUnit;

import java.util.List;

public interface MeasurementUnitService {
    String addUnit(MeasurementUnit measurementUnit);
    List<MeasurementUnit> allUnits();

    boolean deleteUnitById(String id);

    MeasurementUnit findUnitById(String id);
}
