package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    // Get the latest inserted item by itemId in descending order
    Item findTopByOrderByItemIdDesc();
}
