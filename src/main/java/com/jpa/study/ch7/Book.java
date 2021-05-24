package com.jpa.study.ch7;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B") // 구분 컬럼에 입력할 값 지정 : DTYPE 에 M 저장됨
@PrimaryKeyJoinColumn(name = "BOOK_ID") // ID 재정의 : ITEM_ID -> BOOK_ID 로 변경
public class Book extends Item {

    private String author;
    private String isbn;
}
