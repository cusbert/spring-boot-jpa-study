package com.jpa.study.ch6;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    // ManyToOne은 항상 연관 관계의 주인
    // N:1 다대일 양방향 매핑을 사용하자
    @ManyToOne(cascade = CascadeType.ALL) // N:1 다대일 단방향
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    // 연관관계의 주인은 Order -> 다 쪽이 주인
    // mappedBy = "member" 로 주인 아님을 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();

    // 일대일 매핑
    @OneToOne(mappedBy = "member")
    private Locker locker;

    // 연관 관계 메소드
    public void setTeam(Team team) {

        // 기존 팀과 관계를 제거
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        // 무한 루트에 빠지지 않도록 설정
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                ", orders=" + orders +
                ", locker=" + locker +
                '}';
    }
}
