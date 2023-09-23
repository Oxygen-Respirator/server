package com.oxygen.oxygenspring.domain.lang_group.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.lang_group.dto.GroupCreateReqDto;
import com.oxygen.oxygenspring.domain.lang_group.dto.GroupListResDto;
import com.oxygen.oxygenspring.domain.lang_group.service.LangGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class LangGroupController {
    private final LangGroupService langGroupService;

    @GetMapping("")
    public ApiResponse<List<GroupListResDto>> getGroupList() {
        List<GroupListResDto> data = langGroupService.getGroupList();

        return ApiResponse.success(data);
    }

    @PostMapping("")
    public ApiResponse<Void> createGroup(
            @AuthenticationPrincipal User userDetails,
            @RequestBody GroupCreateReqDto reqDto
    ) {
        langGroupService.createGroup(reqDto);

        return ApiResponse.success();
    }
}
