package com.oxygen.oxygenspring.domain.gpt.chat.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.ChatReqDto;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.OpenAiChatResDto;
import com.oxygen.oxygenspring.domain.gpt.chat.service.ChatService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    //    @PostMapping("/api/chat")
    public ApiResponse<OpenAiChatResDto> chat(
            @AuthenticationPrincipal User userDetails,
            @RequestBody @Nullable ChatReqDto reqDto
    ) {

        OpenAiChatResDto data = chatService.chat(userDetails, reqDto);

        return ApiResponse.success(data);
    }
}
