package com.jpa.shop.service;

import com.jpa.shop.domain.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> findItems();

    Item fineOne(Long itemId);

}
