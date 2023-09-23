package com.oxygen.oxygenspring.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MessageDetailResDto {
    private Long id;
    private Integer score;
    private Boolean isResolve;
    private String langGroupName;
    private String userMessage;
    private String answer;
    private String keyword;
    private String tailQuestion;
    private String createdAt;
}
