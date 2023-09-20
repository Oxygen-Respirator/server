package com.oxygen.oxygenspring.domain.gpt.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "ChatReqDto", description = "ChatReqDto")
public class ChatReqDto {
    @Schema(description = "message", example = "message")
    private String message;
}
