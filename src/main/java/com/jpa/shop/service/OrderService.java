package com.jpa.shop.service;

import com.jpa.shop.domain.OrderSearch;
import com.jpa.shop.domain.Order;

import java.util.List;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);
}
