package com.jpa.study.ch12.controller;


import com.jpa.study.ch12.domain.RequestOrder;
import com.jpa.study.ch12.service.ItemService;
import com.jpa.study.ch12.service.MemberService;
import com.jpa.study.ch12.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO DTO 생성
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
