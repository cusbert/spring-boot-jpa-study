package com.jpa.study.ch12.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.jpa.study.ch12.domain.OrderSpec.memberNameLike;
import static com.jpa.study.ch12.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specification.where;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification() {
        return where(memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }

}
