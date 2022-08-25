package com.github.he305.contentaccountverifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ContentAccountVerifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentAccountVerifierApplication.class, args);
	}

}
