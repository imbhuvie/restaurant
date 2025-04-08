package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.entity.Item;
import com.restaurant.Restaurant.entity.Supplier;
import com.restaurant.Restaurant.repository.InventoryRepository;
import com.restaurant.Restaurant.repository.ItemRepository;
import com.restaurant.Restaurant.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AllImpl {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ItemRepository itemRepository;

    public void insertItemSupplierInventory(){
        Item item=new Item();
        item.setItemName("Suger");
        item.setItemDescription("A quality Product.");
        itemRepository.save(item);

        Supplier supplier=new Supplier();
        supplier.setSupplierName("The OG Grocery");
        supplier.setSupplierAddress("Lakhimpur kheri");
        supplier.setSupplierContact("9567890764");
        supplier.setSupplierPersonName("Bhupendra Verma");
        supplier.setSupplierPersonContact("8953834949");
        supplierRepository.save(supplier);

        Inventory inventory=new Inventory();
            inventory.setItem(item);
            inventory.setOpeningStock(0.0);
            inventory.setTotalStock(0.0);
            inventory.setSupplier(supplier);
            inventory.setCurrentPurchases(10.0);
            inventory.setDate(new Date());
            try{
                inventoryRepository.save(inventory);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }




}
