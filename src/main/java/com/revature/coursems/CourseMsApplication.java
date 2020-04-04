package com.revature.coursems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CourseMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseMsApplication.class, args);
	}

}
