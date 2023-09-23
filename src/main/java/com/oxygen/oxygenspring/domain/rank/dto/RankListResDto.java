package com.oxygen.oxygenspring.domain.rank.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RankListResDto {
    @Setter
    private Integer rank;
    private String nickname;
    private Integer resolveCount;
    private Integer totalScore;

    @QueryProjection
    public RankListResDto(String nickname, Integer resolveCount, Integer totalScore) {
        this.nickname = nickname;
        this.resolveCount = resolveCount;
        this.totalScore = totalScore;
    }
}
