package com.jpa.study.ch7;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// 매핑 정보를 상속할 목적으로 사용함, 부모 테이블과 매핑 X
// 공통 매핑 정보를 정의하여 상속
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
