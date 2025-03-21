package com.in28minutes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {

    /**
     * BlockingQueue is used to store messages received from RabbitMQ.
     * LinkedBlockingQueue ensures thread-safe access to messages.
     */
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        messages.add(message);
    }

    @GetMapping("/consume")
    public String consumeMessage() {
        return messages.poll();
    }
}
