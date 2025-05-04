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

// Generate Items ID's
    public String generateNextItemId() {
        Item lastItem = itemRepository.findTopByOrderByItemIdDesc();

        if (lastItem == null) {
            return "IT01";
        }

        String lastId = lastItem.getItemId(); // e.g., IT03
        int number = Integer.parseInt(lastId.substring(2)); // 03 -> 3
        number++;
        return String.format("IT%02d", number); // IT04
    }
    @Override
    public Item getItemById(String itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public Item insertItem(Item item) {
        if(item.getItemId()!=null){
            return itemRepository.save(item);
        }
        item.setItemId(generateNextItemId());
        return itemRepository.save(item);

    }



    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public boolean deleteItemById(String id) {
        try{
        itemRepository.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
