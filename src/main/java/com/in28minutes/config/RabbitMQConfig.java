package com.in28minutes.config;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfig {

    private String queueName;

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Bean
    public Queue myQueue() {
        return new Queue(queueName, true);
    }
}
