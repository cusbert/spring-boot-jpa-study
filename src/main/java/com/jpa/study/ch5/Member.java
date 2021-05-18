package com.jpa.study.ch5;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
@ToString
public class Member {

    @Id
    @Column(name="MEMBER_ID", nullable = false)
    private String id;

    @Column(name="USER_NAME", nullable = false)
    private String username;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setTeam(Team team) {
        this.team = team;
    }
}
