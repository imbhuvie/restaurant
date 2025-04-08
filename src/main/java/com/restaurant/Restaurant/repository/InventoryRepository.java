package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query(value = "select * from inventory where item_id=:itemId order by inventory_id desc limit 1" , nativeQuery = true)
    Inventory findInventoryByItemId(@Param("itemId") Long itemId);
}
