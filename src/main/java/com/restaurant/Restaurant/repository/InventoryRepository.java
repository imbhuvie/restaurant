package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query(value = "select * from inventory where item_id=:itemId order by inventory_id desc limit 1" , nativeQuery = true)
    Inventory findInventoryByItemId(@Param("itemId") Long itemId);

    @Query(value = "SELECT * FROM `demorestaurant`.`inventory` WHERE `item_id` = :itemId  ORDER BY `date` DESC LIMIT 1;" , nativeQuery = true)
    Inventory findItemsWithAvailabilityById(@Param("itemId") long id);
}
