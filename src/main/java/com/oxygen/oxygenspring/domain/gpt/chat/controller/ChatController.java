package com.oxygen.oxygenspring.domain.gpt.chat.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.gpt.chat.dto.ChatReqDto;
import com.oxygen.oxygenspring.domain.gpt.chat.service.ChatService;
import com.oxygen.oxygenspring.domain.user.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat")
    public ApiResponse<Void> chat(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestBody ChatReqDto reqdto) {

        chatService.chat(userDetails, reqdto);

        return ApiResponse.success();
    }
}
