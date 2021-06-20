package com.jpa.study.ch12.service.impl;

import com.jpa.study.ch12.domain.*;
import com.jpa.study.ch12.repository.MemberRepository;
import com.jpa.study.ch12.repository.OrderRepository;
import com.jpa.study.ch12.service.ItemService;
import com.jpa.study.ch12.service.OrderService;
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
        Member member = memberRepository.findById(memberId).orElse(null);;
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

        Order order = orderRepository.findById(orderId).orElse(null);
        order.cancel();
    }

    @Override
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch.toSpecification()); // Specification 조회
        //return orderRepository.search(orderSearch);  // QueryDSL
    }
}
