package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.Item;
import com.restaurant.Restaurant.repository.ItemRepository;
import com.restaurant.Restaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public Item insertItem(Item item) {
        return itemRepository.save(item);

    }



    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
