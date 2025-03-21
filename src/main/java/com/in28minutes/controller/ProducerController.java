package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody GreetingsDto greetingsDto) {
        log.info("Sending message: {}", greetingsDto.getMessage());
        rabbitTemplate.convertAndSend("myQueue", greetingsDto.getMessage());
        log.info("Message sent successfully to RMQ");

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("{ \"statusCode\": 202, \"info\": \"Acknowledged\" }");
    }
}
