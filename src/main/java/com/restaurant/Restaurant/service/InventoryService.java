package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.entity.Supplier;

import java.util.List;

public interface InventoryService {

    Inventory insertInventory(Inventory inventory);
    Inventory findInventoryById(int supplierId);
    List<Inventory> findAllInventory();

    Inventory findInventoryByItemId(Long itemId);


    double getStockById(long id);
}
