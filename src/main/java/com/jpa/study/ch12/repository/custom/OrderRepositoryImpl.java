package com.jpa.study.ch12.repository.custom;

import com.jpa.study.ch12.domain.Order;
import com.jpa.study.ch12.domain.OrderSearch;
import com.jpa.study.ch12.domain.QMember;
import com.jpa.study.ch12.domain.QOrder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {

        QOrder order = QOrder.order;
        QMember member = QMember.member;

        JPQLQuery query = from(order);

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query.leftJoin(order.member, member)
                    .where(member.username.contains(orderSearch.getMemberName()));
        }

        if (orderSearch.getOrderStatus() != null) {
            query.where(order.orderStatus.eq(orderSearch.getOrderStatus()));
        }

        return query.fetch();
    }


}
