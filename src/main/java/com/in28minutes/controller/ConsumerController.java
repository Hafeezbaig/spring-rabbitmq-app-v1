package com.in28minutes.controller;

import com.in28minutes.handler.AsyncMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConsumerController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    private final AsyncMessageHandler messageHandler;

    public ConsumerController(AsyncMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    /**
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html">BlockingQueue</a>
     * is used for thread-safe communication between producer and consumer threads.
     * LinkedBlockingQueue is a thread-safe FIFO queue backed by linked nodes, suitable for message buffering.
     */
    @GetMapping("/consume")
    public String consumeMessage() {
        return messageHandler.consumeMessage();
    }
}
