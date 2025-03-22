package com.in28minutes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Slf4j
public class AsyncMessageHandler {

    private final BlockingQueue<String> messages;

    public AsyncMessageHandler() {
        this.messages = new LinkedBlockingQueue<>();
    }

    @RabbitListener(queues = "${rabbitmq.queue-name}")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        messages.add(message);
    }

    public String consumeMessage() {
        return messages.poll();
    }
}
