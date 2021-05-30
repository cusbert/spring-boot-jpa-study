package com.jpa.study.ch8;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MEMBER")
@Setter
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID", nullable = false)
    private Long id;

    @Column(name="USER_NAME", nullable = false)
    private String username;

    // 연관관계의 주인은 Member
    // @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();


}
