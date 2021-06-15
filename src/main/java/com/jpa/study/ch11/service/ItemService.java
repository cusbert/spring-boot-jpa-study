package com.jpa.study.ch11.service;

import com.jpa.study.ch11.domain.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> findItems();

    Item fineOne(Long itemId);

}
