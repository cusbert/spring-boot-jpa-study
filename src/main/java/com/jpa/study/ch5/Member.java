package com.jpa.study.ch5;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "AGE", nullable = true)
    private Integer age;

    @Column(name = "CITY", nullable = true)
    private String city;

    @Column(name = "STREET", nullable = true)
    private String street;

    @Column(name = "ZIP_CD", nullable = true)
    private String zipCode;

    // 연관관계의 주인은 Order -> 다 쪽이 주인
    // mappedBy = "member" 로 주인 아님을 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();

    // 연관관계의 주인은 Member
    // ManyToOne은 항상 연관 관계의 주인
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // 연관 관계 메소드
    public void setTeam(Team team) {

        // 기존 팀과 관계를 제거
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this); // 양방향 설정을 위함
    }


}
