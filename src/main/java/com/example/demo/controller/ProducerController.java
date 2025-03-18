package com.example.demo.controller;  // Change this to match your package name

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")  // API base URL: /producer
@Slf4j  // Enables logging using Lombok
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;  // Inject RabbitMQ template

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/{message}")  // Example: /producer/HelloWorld
    public String sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend("myQueue", message);  // Send message to RabbitMQ queue
        log.info("Sent message: {}", message);  // Logs the message
        return "Message sent: " + message;
    }
}
