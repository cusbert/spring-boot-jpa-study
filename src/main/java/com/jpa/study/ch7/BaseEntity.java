package com.jpa.study.ch7;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

// 매핑 정보를 상속할 목적으로 사용함, 부모 테이블과 매핑 X
// 공통 매핑 정보를 정의하여 상속
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    private Date createdDate;
    private Date modifiedDate;

}
