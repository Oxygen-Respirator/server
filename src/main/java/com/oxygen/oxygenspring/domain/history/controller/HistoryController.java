package com.oxygen.oxygenspring.domain.history.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.history.dto.HistoryListResDto;
import com.oxygen.oxygenspring.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("")
    public ApiResponse<HistoryListResDto> getHistoryList(
            @AuthenticationPrincipal User userDetails
    ) {
        HistoryListResDto data = historyService.getHistoryList(userDetails.getUsername());

        return ApiResponse.success(data);
    }
}
