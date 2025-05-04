package com.restaurant.Restaurant.service;


import com.restaurant.Restaurant.entity.Item;

import java.util.List;

public interface ItemService {

    Item getItemById(String itemId);
    Item insertItem(Item item);
    List<Item> findAllItems();


    boolean deleteItemById(String id);
}
