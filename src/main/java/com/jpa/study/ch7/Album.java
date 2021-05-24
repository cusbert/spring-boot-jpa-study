package com.jpa.study.ch7;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 구분 컬럼에 입력할 값 지정 : DTYPE 에 A 저장됨
public class Album extends Item {

    private String artist;
}
