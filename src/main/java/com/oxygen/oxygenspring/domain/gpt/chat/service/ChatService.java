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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final GptChatRepository gptChatRepository;
    private final WebClientConnector webClientConnector;

    public OpenAiChatResDto chat(UserDetailsImpl userDetails, ChatReqDto reqdto) {
//        String username = userDetails.getUsername();

        List<OpenAiChatReqDto.Message> messageList = new ArrayList<>();
        OpenAiChatReqDto.Message.builder()
                .role("user")
                .content("hello")
                .name(null)
                .functionCall(null)
                .build();

        OpenAiChatReqDto openAiChatReqDto = OpenAiChatReqDto.builder()
                .model("gpt-3.5-turbo-0914")
                .messages(messageList)
                .functionCall(null)
                .temperature(0.7)
                .topP(null)
                .n(null)
                .stream(false)
                .maxTokens(null)
                .presencePenalty(null)
                .frequencyPenalty(null)
//                .user(username)
                .build();

        return webClientConnector.callOpenApi(
                HttpMethod.POST,
                "/v1/chat/completions",
                null,
                null,
                openAiChatReqDto,
                OpenAiChatResDto.class);
    }
}
