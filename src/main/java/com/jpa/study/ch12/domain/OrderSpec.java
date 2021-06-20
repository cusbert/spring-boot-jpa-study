package com.jpa.study.ch12.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;

public class OrderSpec {

    public static Specification<Order> memberNameLike(final String memberName) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (ObjectUtils.isEmpty(memberName)) {
                    return null;
                }

                Join<Order, Member> m = root.join("member", JoinType.INNER); // 회원과 조인
                return criteriaBuilder.like(m.<String>get("name"), "%" + memberName + "%");
            }
        };
    }

    public static Specification<Order> orderStatusEq(final OrderStatus orderStatus) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (orderStatus == null) {
                    return null;
                }

                return criteriaBuilder.equal(root.get("status"), orderStatus);
            }
        };
    }
}
