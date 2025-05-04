package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.Employee;
import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.repository.InventoryRepository;
import com.restaurant.Restaurant.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public String generateNextEmpId() {
        Inventory lastEmployee = inventoryRepository.findTopByOrderByInventoryIdDesc();

        if (lastEmployee == null) {
            return "ENV01";
        }

        String lastId = lastEmployee.getInventoryId(); // e.g., EMP04
        int number = Integer.parseInt(lastId.substring(3)); // 04 → 4
        number++;
        return String.format("ENV%02d", number); // EMP05
    }
    @Override
    public Inventory insertInventory(Inventory inventory) {
        try{
            System.out.println("Before saving to DB : "+inventory);
            inventory.setInventoryId(generateNextEmpId());
            return inventoryRepository.save(inventory);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Inventory findInventoryById(String supplierId) {
        return null;
    }

    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findInventoryByItemId(String itemId) {
        try{
            System.out.println("----------IMPL-- TRUE------------");
            return  inventoryRepository.findInventoryByItemId(itemId);
        }catch (Exception e){
            System.out.println("----------IMPL-- FALSE------------");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getStockById(String id) {
        Inventory inventory =  inventoryRepository.findItemsWithAvailabilityById(id);
//        System.out.println(inventory);
//        System.out.println(inventory.getTotalStock());
        return inventory.getTotalStock();
    }
}
