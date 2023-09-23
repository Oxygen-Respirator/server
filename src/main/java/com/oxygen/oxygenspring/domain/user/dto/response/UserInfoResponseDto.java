package com.oxygen.oxygenspring.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String userId;
    private String userNickname;
    private Integer remainAnswerCount;
    private Integer maxAnswerCount;

    @Builder
    public UserInfoResponseDto(String userId, String userNickname, Integer remainAnswerCount, Integer maxAnswerCount) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.remainAnswerCount = remainAnswerCount;
        this.maxAnswerCount = maxAnswerCount;
    }
}
