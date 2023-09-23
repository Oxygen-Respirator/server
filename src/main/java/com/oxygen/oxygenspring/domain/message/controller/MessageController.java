package com.oxygen.oxygenspring.domain.message.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.message.dto.MessageCreateReqDto;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import com.oxygen.oxygenspring.domain.message.service.MessageService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("")
    public ApiResponse<List<MessageDetailResDto>> getMessage(
            @AuthenticationPrincipal User userDetails,
            @RequestParam("group-id") @Nullable Long groupId
    ) {
        List<MessageDetailResDto> data = messageService.getDetailMessage(groupId, userDetails.getUsername());

        return ApiResponse.success(data);
    }

    @PostMapping("/{group-id}")
    public ApiResponse<MessageDetailResDto> createMessage(
            @AuthenticationPrincipal User userDetails,
            @PathVariable("group-id") Long groupId,
            @RequestBody MessageCreateReqDto reqDto
    ) {
        MessageDetailResDto data = messageService.createMessage(userDetails, groupId, reqDto);

        return ApiResponse.success(data);
    }
}
