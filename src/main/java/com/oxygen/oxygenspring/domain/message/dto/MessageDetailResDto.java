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
    private String role;
    private String message;
    private String createdAt;
}
