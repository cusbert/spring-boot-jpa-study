package com.jpa.shop.service.impl;

import com.jpa.shop.domain.*;
import com.jpa.shop.repository.MemberRepository;
import com.jpa.shop.repository.OrderRepository;
import com.jpa.shop.service.ItemService;
import com.jpa.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemService itemService;


    @Override
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회 : member, item
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.fineOne(itemId);

        // 2-1 배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());
        // 2-2 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        // 2-3 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    @Override
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
