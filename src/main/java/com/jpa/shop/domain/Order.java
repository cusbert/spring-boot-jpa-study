package com.jpa.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(name = "FK_MEMBER_MEMBER_ID"))
    private Member member;


    // 연관 관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }


}
