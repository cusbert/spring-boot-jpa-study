package com.jpa.study.ch12.repository.custom;

import com.jpa.study.ch12.domain.Order;
import com.jpa.study.ch12.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {

    public List<Order> search(OrderSearch orderSearch);
}
