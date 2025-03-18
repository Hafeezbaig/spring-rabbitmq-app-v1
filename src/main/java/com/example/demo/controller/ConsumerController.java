package com.example.demo.controller;  // Ensure this matches your project structure

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/consumer")  // API base URL: /consumer
@Slf4j  // Enables logging using Lombok
public class ConsumerController {

    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @RabbitListener(queues = "myQueue")  // Listens for messages in "myQueue"
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        messages.add(message);
    }

    @GetMapping  // GET request to retrieve messages
    public String consumeMessage() {
        return messages.poll();  // Returns and removes the first message in the queue
    }
}
