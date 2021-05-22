package com.jpa.study.ch6;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID", nullable = false)
    private Long id;

    @Column(name = "TEAM_NAME", nullable = false)
    private String name;

    // 연관관계의 주인은 Member -> 다 쪽이 주인
    // mappedBy="team" 으로 주인 아님을 설정
    // N:1 다대일 단방향
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<Member>();


    // 1:N 일대다 단방향
    // 단점 : 매핑한 객체가 관리하는 외리키가 다른 테이블에 있음
    //       -> 연관관계 처리를 위해 insert 후 update 문이 추가로 필요함
    // 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자
    /*
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<Member>();
    */

    public void addMember(Member member) {
        this.members.add(member);
        // 무한루트에 빠지지 않도록 설정
        if (member.getTeam() != this) {
            member.setTeam(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
