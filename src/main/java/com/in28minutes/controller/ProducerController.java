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

    @Value("${rabbitmq.queue-name}")
    private String queueName;

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
        rabbitTemplate.convertAndSend(queueName, msg);
        log.info("Message sent successfully to RMQ");

        return ResponseEntity.accepted().body(Map.of(
                "statusCode", 202,
                "info", "Acknowledged"
        ));
    }

    /**
     * GET-based producer endpoint (for quick testing via browser)
     * Example: http://localhost:9100/api/produce?message=hello
     */
    @GetMapping("/produce")
    public ResponseEntity<?> sendMessageViaQueryParam(@RequestParam String message) {
        log.info("Sending message via GET: {}", message);
        rabbitTemplate.convertAndSend(queueName, message);
        log.info("Message sent successfully to RMQ (via GET)");

        return ResponseEntity.accepted().body(Map.of(
                "statusCode", 202,
                "info", "Acknowledged GET"
        ));
    }
}
