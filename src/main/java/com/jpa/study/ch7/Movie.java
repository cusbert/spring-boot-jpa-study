package com.jpa.study.ch7;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") // 구분 컬럼에 입력할 값 지정 : DTYPE 에 M 저장됨
public class Movie extends Item {

    private String director;
    private String actor;
}
