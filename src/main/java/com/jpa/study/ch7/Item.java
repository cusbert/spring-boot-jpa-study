package com.jpa.study.ch7;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 매핑전략을 join 전략으로 지정
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼 지정
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private Long name;
    private int price;
}
