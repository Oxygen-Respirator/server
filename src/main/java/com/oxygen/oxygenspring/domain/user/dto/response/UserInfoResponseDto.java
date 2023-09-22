package com.oxygen.oxygenspring.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String userId;
    private String userNickname;

    @Builder
    public UserInfoResponseDto(String userId, String userNickname) {
        this.userId = userId;
        this.userNickname = userNickname;
    }
}
