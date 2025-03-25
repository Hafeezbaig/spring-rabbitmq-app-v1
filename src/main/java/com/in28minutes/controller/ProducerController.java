package com.in28minutes.controller;

import com.in28minutes.dto.Greetings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * RabbitTemplate is the core class provided by Spring AMQP for publishing messages to RabbitMQ.
 * Official Docs: https://docs.spring.io/spring-amqp/docs/current/reference/html/#amqp-template
 */
@RestController
@RequestMapping("/api")
public class ProducerController {

    private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange-name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Example curl:
     * curl -X POST http://localhost:9100/api/produce \
     *      -H "Content-Type: application/json" \
     *      -d '{"message": "Hello, RabbitMQ!"}'
     */
    @PostMapping("/produce")
    public ResponseEntity<?> sendMessage(@RequestBody Greetings greetings) {
        String msg = greetings.message();
        log.info("Sending message: {}", msg);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        log.info("Message sent successfully to RMQ (via Direct Exchange)");

        return ResponseEntity.accepted().body(Map.of(
                "statusCode", 202,
                "info", "Acknowledged"
        ));
    }
}
