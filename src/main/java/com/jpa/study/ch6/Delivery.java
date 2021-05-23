package com.jpa.study.ch6;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery", cascade = CascadeType.ALL)
    private Order order;

//    public void setOrder(Order order) {
//        this.order = order;
//        order.setDelivery(this);
//    }
}
