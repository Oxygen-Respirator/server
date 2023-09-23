package com.oxygen.oxygenspring.domain.message.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.message.dto.MessageCreateReqDto;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import com.oxygen.oxygenspring.domain.message.service.MessageService;
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

    @GetMapping("/{group-id}")
    public ApiResponse<List<MessageDetailResDto>> getMessage(
            @AuthenticationPrincipal User userDetails,
            @PathVariable("group-id") Long groupId
    ) {
        List<MessageDetailResDto> data = messageService.getDetailMessage(groupId, userDetails.getUsername());

        return ApiResponse.success(data);
    }

    @PostMapping("/{group-id}")
    public ApiResponse<String> createMessage(
            @AuthenticationPrincipal User userDetails,
            @PathVariable("group-id") Long groupId,
            @RequestBody MessageCreateReqDto reqDto
    ) {
//        MessageDetailResDto data = messageService.createMessage(userDetails, groupId, reqDto);

        String data = """
                {
                    "id": 0,
                    "score": 57,
                    "isResolve": true,
                    "langGroupName": "Java",
                    "userMessage": "여기에 유저의 질문이 그대로 들어옵니당",
                    "answer": "질문에 대한 답변",
                    "keyword": "키워드",
                    "tailQuestion": "꼬리질문",
                    "createdAt": "생성일자"
                }""";

        return ApiResponse.success(data);
    }
}
