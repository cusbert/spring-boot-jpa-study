package com.jpa.study.ch11.service;

import com.jpa.study.ch11.domain.OrderSearch;
import com.jpa.study.ch11.domain.Order;

import java.util.List;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);
}
