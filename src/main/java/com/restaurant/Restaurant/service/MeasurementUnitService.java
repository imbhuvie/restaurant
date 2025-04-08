package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.entity.MeasurementUnit;

import java.util.List;

public interface MeasurementUnitService {
    MeasurementUnit addUnit(MeasurementUnit measurementUnit);
    List<MeasurementUnit> allUnits();
}
