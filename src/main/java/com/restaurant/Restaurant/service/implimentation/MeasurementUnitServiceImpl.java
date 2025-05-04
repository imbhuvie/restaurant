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

    public String generateNextUnitId() {
        MeasurementUnit lastUnit = measurementUnitRepository.findTopByOrderByIdDesc();
        if (lastUnit == null) {
            return "UN01";
        }

        String lastId = lastUnit.getId(); // e.g., UN05
        int number = Integer.parseInt(lastId.substring(2)); // 05 → 5
        number++;
        return String.format("UN%02d", number); // UN06
    }
    @Override
    public String addUnit(MeasurementUnit measurementUnit) {
        if(measurementUnit.getId()==null){
        measurementUnit.setId(generateNextUnitId());
        }
        try {
            List<MeasurementUnit> existUnit = measurementUnitRepository.findByUnitName(measurementUnit.getUnitName());

            if (existUnit.isEmpty()) {
                measurementUnitRepository.save(measurementUnit);
                return "added";
            } else {
                for (MeasurementUnit unit : existUnit) {
                    if ((unit.getSymbol().equalsIgnoreCase(measurementUnit.getSymbol()))) {
                        return "exists";
                    }
                }
                measurementUnitRepository.save(measurementUnit);
                return "added";
            }
        } catch (Exception e) {
            return "error";
        }
    }



    @Override
    public List<MeasurementUnit> allUnits() {
        return measurementUnitRepository.findAll();
    }

    @Override
    public boolean deleteUnitById(String id) {
        try{
            measurementUnitRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public MeasurementUnit findUnitById(String id) {
        return measurementUnitRepository.findById(id).get();
    }
}
