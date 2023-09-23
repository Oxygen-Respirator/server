package com.oxygen.oxygenspring.domain.history.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class HistoryListResDto {
    private Integer solvedCount;
    private Integer triedCount;
    private Integer rank;
    private String totalScore;

    @QueryProjection
    public HistoryListResDto(Integer solvedCount, Integer triedCount, Integer rank, String totalScore) {
        this.solvedCount = solvedCount;
        this.triedCount = triedCount;
        this.rank = rank;
        this.totalScore = totalScore;
    }
}
