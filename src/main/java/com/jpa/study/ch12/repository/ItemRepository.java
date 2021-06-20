package com.jpa.study.ch12.repository;

import com.jpa.study.ch12.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
