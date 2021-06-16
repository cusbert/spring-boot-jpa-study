package com.jpa.study.ch12.controller;

import com.jpa.study.ch12.domain.Album;
import com.jpa.study.ch12.domain.Item;
import com.jpa.study.ch12.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    // 상품 목록
    @GetMapping("/items")
    public @ResponseBody List<Item> list() {
        List<Item> items = itemService.findItems();
        return items;
    }


    // 상품 등록
    @PostMapping("/items")
    public ResponseEntity<String> registerItem(@RequestBody Album item) {
        itemService.saveItem(item);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }


    // 상품 수정
    @PutMapping("/items/{itemId}")
    public ResponseEntity<String> updateItem(@RequestBody Album item) {
        itemService.saveItem(item);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }



}
