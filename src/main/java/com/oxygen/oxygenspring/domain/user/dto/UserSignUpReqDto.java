package com.oxygen.oxygenspring.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@Schema(name = "User Sign Up Request")
public class UserSignUpReqDto extends UserReqDto {

    @Schema(description = "유저 닉네임", example = "nickname")
    @Length(min = 1, max = 10, message = "유저 닉네임은 1~10자리로 입력해주세요.")
    private String userNickname;
}
