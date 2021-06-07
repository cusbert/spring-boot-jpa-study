package com.jpa.shop.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID", nullable = false)
    private Long id;

    @Column(name="USER_NAME", nullable = false)
    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

}
