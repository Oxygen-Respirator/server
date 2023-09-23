package com.oxygen.oxygenspring.domain.message.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KafkaResDto {
    private String score;
    private String answer;
    private String keyword;
    private String tailQuestion;
    private String etc;
}
