package com.jpa.study.ch8;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID", nullable = false)
    private Long id;

    @Column(name = "TEAM_NAME", nullable = false)
    private String name;

}
