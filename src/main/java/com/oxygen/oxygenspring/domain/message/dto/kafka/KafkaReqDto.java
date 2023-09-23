package com.oxygen.oxygenspring.domain.message.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KafkaReqDto {
    private Long userId;
    private Long lang;
    private String message;
}
