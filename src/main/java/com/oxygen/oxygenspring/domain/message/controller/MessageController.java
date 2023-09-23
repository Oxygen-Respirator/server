package com.oxygen.oxygenspring.domain.message.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import com.oxygen.oxygenspring.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{group-id}")
    public ApiResponse<List<MessageDetailResDto>> getMessage(
            @AuthenticationPrincipal User userDetails,
            @PathVariable("group-id") Long groupId
    ) {
        List<MessageDetailResDto> data = messageService.getDetailMessage(groupId);

        return ApiResponse.success(data);
    }

}
