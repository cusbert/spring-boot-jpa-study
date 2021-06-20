package com.jpa.study.ch12.service.impl;

import com.jpa.study.ch12.domain.Item;
import com.jpa.study.ch12.repository.ItemRepository;
import com.jpa.study.ch12.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
        return itemRepository.findById(itemId).orElse(null);
    }
}
