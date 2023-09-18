package com.oxygen.oxygenspring.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "User Login Request")
public class UserReqDto {
    @Schema(description = "유저 ID", example = "test_id")
    @NotBlank(message = "유저 ID는 필수값입니다.")
    private String userId;

    @Schema(description = "유저 PW", example = "test_pw")
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String userPw;
}
