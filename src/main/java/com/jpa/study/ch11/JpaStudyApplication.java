package com.jpa.study.ch11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.jpa.study.ch11")
@EntityScan("com.jpa.study.ch11")
@EnableJpaRepositories("com.jpa.study.ch11")
public class JpaStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaStudyApplication.class, args);
	}

}
