package com.jpa.study.ch7;

import javax.persistence.*;

@Entity
// @Inheritance(strategy = InheritanceType.JOINED) // 매핑전략을 join 전략으로 지정
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 클래스마다 테이블 전략
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼 지정
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private Long name;
    private int price;
}
