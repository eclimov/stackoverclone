package com.roadmap.stackoverclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // TODO: remove this 'exclude' when enabling real security
public class StackovercloneApplication {
	// https://www.youtube.com/watch?v=nGfeSo52_8A
	// https://www.youtube.com/watch?v=BmBr5diz8WA&t=2988s
	// https://restfulapi.net/resource-naming/
	// Swagger: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
	/// Learn more about collections and O(n) notations

	// oauth: https://medium.com/better-programming/secure-a-spring-boot-rest-api-with-json-web-token-reference-to-angular-integration-e57a25806c50

	// Voters: https://www.baeldung.com/spring-security-custom-voter

	public static void main(String[] args) {
		SpringApplication.run(StackovercloneApplication.class, args);
	}

}
