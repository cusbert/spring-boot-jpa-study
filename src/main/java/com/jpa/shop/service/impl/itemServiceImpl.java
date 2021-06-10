package com.jpa.shop.service.impl;

import com.jpa.shop.domain.Item;
import com.jpa.shop.repository.ItemRepository;
import com.jpa.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class itemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item fineOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
