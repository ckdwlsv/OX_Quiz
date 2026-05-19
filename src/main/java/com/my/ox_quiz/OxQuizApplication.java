package com.my.ox_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OxQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxQuizApplication.class, args);
	}

}
