package com.oxygen.oxygenspring.domain.gpt.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxygen.oxygenspring._common.web_client.WebClientConnector;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.ChatReqDto;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.OpenAiChatResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final WebClientConnector webClientConnector;

    public OpenAiChatResDto chat(User userDetails, ChatReqDto reqDto) {
        String data = """
                {
                    "usage": {
                      "total_tokens": 17,
                      "completion_tokens": 9,
                      "prompt_tokens": 8
                    },
                    "choices": [
                      {
                        "finish_reason": "stop",
                        "message": {
                          "content": "Hello! How can I assist you today?",
                          "role": "assistant"
                        },
                        "index": 0
                      }
                    ],
                    "model": "gpt-3.5-turbo-0613",
                    "created": 1695360216,
                    "object": "chat.completion",
                    "id": "chatcmpl-81SvYGUP1oRh7fDv6mkRGi3uPXSSk"
                }""";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(data, OpenAiChatResDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


//        String username = userDetails.getUsername();

//        List<OpenAiChatReqDto.Message> messageList = new ArrayList<>();
//        messageList.add(OpenAiChatReqDto.Message.builder()
//                .role("user")
//                .content("hello")
//                .name(null)
//                .functionCall(null)
//                .build());
//
//        OpenAiChatReqDto openAiChatReqDto = OpenAiChatReqDto.builder()
//                .model("gpt-3.5-turbo")
//                .messages(messageList)
//                .functionCall(null)
//                .temperature(0.7)
//                .topP(null)
//                .n(null)
//                .stream(false)
//                .maxTokens(null)
//                .presencePenalty(null)
//                .frequencyPenalty(null)
////                .user(username)
//                .build();
//
//        return webClientConnector.callOpenApi(
//                HttpMethod.POST,
//                "/v1/chat/completions",
//                null,
//                null,
//                openAiChatReqDto,
//                OpenAiChatResDto.class);
    }
}
