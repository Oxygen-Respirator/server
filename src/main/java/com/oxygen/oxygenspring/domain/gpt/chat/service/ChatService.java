package com.oxygen.oxygenspring.domain.gpt.chat.service;

import com.oxygen.oxygenspring._common.web_client.WebClientConnector;
import com.oxygen.oxygenspring.db.repository.GptChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final GptChatRepository gptChatRepository;
    private final WebClientConnector webClientConnector;

}
