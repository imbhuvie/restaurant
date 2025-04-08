package com.restaurant.Restaurant.service;


import com.restaurant.Restaurant.entity.Item;

import java.util.List;

public interface ItemService {

    Item getItemById(Long itemId);
    Item insertItem(Item item);
    List<Item> findAllItems();



}
