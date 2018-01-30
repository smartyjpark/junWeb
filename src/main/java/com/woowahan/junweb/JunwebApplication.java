package com.woowahan.junweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JunwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunwebApplication.class, args);
	}
}
