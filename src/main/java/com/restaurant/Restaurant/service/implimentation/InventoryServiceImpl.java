package com.restaurant.Restaurant.service.implimentation;

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
    @Override
    public Inventory insertInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findInventoryById(int supplierId) {
        return null;
    }

    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findInventoryByItemId(Long itemId) {
        try{
            return  inventoryRepository.findInventoryByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
