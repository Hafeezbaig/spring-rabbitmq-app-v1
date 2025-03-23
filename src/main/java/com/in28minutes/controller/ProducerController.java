package com.in28minutes.controller;

import com.in28minutes.dto.GreetingsDto;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProducerController {

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
    public ResponseEntity<?> sendMessage(@RequestBody GreetingsDto greetingsDto) {
        String msg = greetingsDto.getMessage();
        log.info("Sending message: {}", msg);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        log.info("Message sent successfully to RMQ (via Direct Exchange)");

        return ResponseEntity.accepted().body(Map.of(
                "statusCode", 202,
                "info", "Acknowledged"
        ));
    }
}
