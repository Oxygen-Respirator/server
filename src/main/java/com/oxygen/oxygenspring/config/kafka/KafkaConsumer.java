package com.oxygen.oxygenspring.config.kafka;

import com.oxygen.oxygenspring._common.utils.Utils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "chat-django", groupId = "gpt")
    public void listenGroup1(String message) {
        String massage = Utils.unicodeDecode(message);
    }
}