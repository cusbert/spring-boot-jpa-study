package com.jpa.shop.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
public class MOVIE extends Item{

    private String director;
    private String actor;
}
