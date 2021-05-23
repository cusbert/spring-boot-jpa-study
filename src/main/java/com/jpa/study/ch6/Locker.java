package com.jpa.study.ch6;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LOCKER")
@Getter
@Setter
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "LOCKER_NAME", nullable = true)
    private String name;

    // 일대일 양방향 매핑
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
