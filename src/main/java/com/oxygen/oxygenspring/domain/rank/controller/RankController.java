package com.oxygen.oxygenspring.domain.rank.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.rank.dto.RankListResDto;
import com.oxygen.oxygenspring.domain.rank.service.RankService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rank")
public class RankController {
    private final RankService rankService;

    @GetMapping("")
    public ApiResponse<List<RankListResDto>> getRankList(
            @RequestParam(value = "group-id") @Nullable Long groupId
    ) {
        List<RankListResDto> data = rankService.getRankList(groupId);

        return ApiResponse.success(data);
    }
}
