package com.jpa.study.ch11.service.impl;

import com.jpa.study.ch11.domain.Item;
import com.jpa.study.ch11.repository.ItemRepository;
import com.jpa.study.ch11.service.ItemService;
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
        return itemRepository.findOne(itemId);
    }
}
