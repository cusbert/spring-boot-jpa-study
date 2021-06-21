package com.jpa.study.ch12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.jpa.study.ch12")
@EntityScan("com.jpa.study.ch12")
@EnableJpaRepositories("com.jpa.study.ch12")
public class JpaStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaStudyApplication.class, args);
	}

}
