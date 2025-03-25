package com.in28minutes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMessageBrokerApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringMessageBrokerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringMessageBrokerApplication.class, args);
		log.info("✅ Application started successfully.");
	}
}
