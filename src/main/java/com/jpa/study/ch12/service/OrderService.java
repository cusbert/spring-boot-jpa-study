package com.jpa.study.ch12.service;

import com.jpa.study.ch12.domain.Order;
import com.jpa.study.ch12.domain.OrderSearch;

import java.util.List;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);
}
