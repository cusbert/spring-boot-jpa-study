package com.jpa.shop.controller;


import com.jpa.shop.domain.Order;
import com.jpa.shop.domain.OrderSearch;
import com.jpa.shop.domain.RequestOrder;
import com.jpa.shop.service.ItemService;
import com.jpa.shop.service.MemberService;
import com.jpa.shop.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    // 주문
    @PostMapping("/orders/{memberId}")
    public ResponseEntity<String> order(
            @PathVariable("memberId") Long memberId,
            @RequestBody RequestOrder requestOrder) {
        orderService.order(memberId, requestOrder.getItemId(), requestOrder.getCount());
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

/*    @GetMapping("/orders")
    public List<Order> orders() {
        OrderSearch orderSearch = new OrderSearch();
        List<Order> orders = orderService.findOrders(orderSearch);
        return orders;
    }

    @PutMapping("/orders/{orderId}/cancel")
    public ResponseEntity<String> cancel(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }*/


}
