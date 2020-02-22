package com.roadmap.stackoverclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // TODO: remove this 'exclude' when enabling real security
public class StackovercloneApplication {
	// https://www.youtube.com/watch?v=nGfeSo52_8A
	// https://www.youtube.com/watch?v=BmBr5diz8WA&t=2988s
	// Swagger: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

	public static void main(String[] args) {
		SpringApplication.run(StackovercloneApplication.class, args);
	}

}
