package com.in28minutes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringMessageBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMessageBrokerApplication.class, args);
		log.info("✅ Application started successfully.");
	}
}
