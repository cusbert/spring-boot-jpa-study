package com.jpa.study.ch6;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_ITEM")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "ITEM_NAME", nullable = false)
    private String name;
    @Column(name = "PRICE", nullable = false)
    private int price;
    @Column(name = "STOCK_QNTY", nullable = false)
    private int stockQuantity;

}
