package com.example.demo.config;  // Change this if your package name is different

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Marks this as a Spring Boot configuration class
public class RabbitMQConfig {

    @Bean  // Creates a RabbitMQ queue when the application starts
    public Queue myQueue() {
        return new Queue("myQueue", true);  // Queue name: "myQueue", durable = true
    }
}
