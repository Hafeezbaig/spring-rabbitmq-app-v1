package com.in28minutes.controller;

import com.in28minutes.service.AsyncMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ConsumerController {

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
