package com.oxygen.oxygenspring.domain.user.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring.domain.user.dto.UserReqDto;
import com.oxygen.oxygenspring.domain.user.dto.UserSignUpReqDto;
import com.oxygen.oxygenspring.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "01. User", description = "회원관리 및 로그인")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "userId와 userPw는 필수값 <br> userNickname은 1글자 ~ 10글자만 가능")
    public ApiResponse<String> signUp(
            @RequestBody @Valid UserSignUpReqDto requestDto
    ) {
        userService.signUp(requestDto);

        return ApiResponse.success();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "userId와 userPw는 필수값 <br> Token은 Authorization 헤더에 담아서 보냄")
    public ApiResponse<String> login(
            @RequestBody UserReqDto requestDto,
            HttpServletResponse response
    ) {
        response.setHeader("Authorization", userService.login(requestDto));
        return ApiResponse.success();
    }
}
