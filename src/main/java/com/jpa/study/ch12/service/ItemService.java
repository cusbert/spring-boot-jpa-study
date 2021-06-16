package com.jpa.study.ch12.service;

import com.jpa.study.ch12.domain.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> findItems();

    Item fineOne(Long itemId);

}
