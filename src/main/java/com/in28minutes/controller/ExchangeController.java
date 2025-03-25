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
 * Demonstrates how to send messages using Fanout and Topic exchanges.
 */
@RestController
@RequestMapping("/api")
public class ExchangeController {

    private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.fanout-exchange}")
    private String fanoutExchange;

    @Value("${rabbitmq.topic-exchange}")
    private String topicExchange;

    public ExchangeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Example:
     * curl -X POST http://localhost:9100/api/fanout \
     *      -H "Content-Type: application/json" \
     *      -d '{"message": "Broadcast to all"}'
     */
    @PostMapping("/fanout")
    public ResponseEntity<?> sendToFanout(@RequestBody Greetings greetings) {
        String msg = greetings.message();
        log.info("Sending to fanout exchange: {}", msg);
        rabbitTemplate.convertAndSend(fanoutExchange, "", msg); // No routing key for fanout
        return ResponseEntity.ok(Map.of("info", "Sent to fanout exchange"));
    }

    /**
     * Example:
     * curl -X POST http://localhost:9100/api/topic?key=user.created \
     *      -H "Content-Type: application/json" \
     *      -d '{"message": "User created event"}'
     */
    @PostMapping("/topic")
    public ResponseEntity<?> sendToTopic(@RequestParam String key, @RequestBody Greetings greetings) {
        String msg = greetings.message();
        log.info("Sending to topic exchange with key [{}]: {}", key, msg);
        rabbitTemplate.convertAndSend(topicExchange, key, msg);
        return ResponseEntity.ok(Map.of("info", "Sent to topic exchange with routing key: " + key));
    }
}
