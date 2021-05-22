package com.jpa.study.ch6;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID", nullable = false)
    private Long id;

    @Column(name="USER_NAME", nullable = false)
    private String username;

    // 연관관계의 주인은 Member
    // ManyToOne은 항상 연관 관계의 주인
    @ManyToOne(cascade = CascadeType.ALL) // N:1 다대일 단방향
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    // 연관 관계 메소드
    public void setTeam(Team team) {

        this.team = team;
        // 무한 루트레 빠지지 않도록 설정
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }


}
