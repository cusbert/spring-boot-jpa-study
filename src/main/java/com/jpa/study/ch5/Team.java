package com.jpa.study.ch5;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM")
@AllArgsConstructor
public class Team {

    @Id
    @Column(name = "TEAM_ID", nullable = false)
    private String id;

    @Column(name = "TEAM_NAME", nullable = false)
    private String name;
}
