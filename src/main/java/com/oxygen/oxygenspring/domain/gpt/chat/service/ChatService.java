package com.oxygen.oxygenspring.domain.gpt.chat.service;

import com.oxygen.oxygenspring._common.web_client.WebClientConnector;
import com.oxygen.oxygenspring.db.repository.GptChatRepository;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.ChatReqDto;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.OpenAiChatReqDto;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.OpenAiChatResDto;
import com.oxygen.oxygenspring.domain.user.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final GptChatRepository gptChatRepository;
    private final WebClientConnector webClientConnector;

    public void chat(UserDetailsImpl userDetails, ChatReqDto reqdto) {
        String username = userDetails.getUsername();

        OpenAiChatReqDto openAiChatReqDto = new OpenAiChatReqDto();

        OpenAiChatResDto result = webClientConnector.<OpenAiChatReqDto, OpenAiChatResDto>callOpenApiBuilder()
                .method(HttpMethod.POST)
                .path("/v1/chat/completions")
                .headers(null)
                .params(null)
                .requestBody(openAiChatReqDto)
                .responseType(OpenAiChatResDto.class)
                .build();

    }
}
