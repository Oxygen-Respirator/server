package com.oxygen.oxygenspring.domain.message.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class MessageConsumer {
    private final ConcurrentHashMap<String, CompletableFuture<String>> futures = new ConcurrentHashMap<>();

    @KafkaListener(topics = "response_chat", groupId = "gpt")
    public void listen(String message, @Header("correlationId") String correlationId) {
        CompletableFuture<String> future = futures.get(correlationId);
        if (future != null) {
            future.complete(message);
        }
    }

    public void register(String correlationId, CompletableFuture<String> future) {
        futures.put(correlationId, future);
    }
}
