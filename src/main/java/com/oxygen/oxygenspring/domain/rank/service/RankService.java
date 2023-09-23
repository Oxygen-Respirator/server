package com.oxygen.oxygenspring.domain.rank.service;

import com.oxygen.oxygenspring.db.repository.join.RankJoinRepository;
import com.oxygen.oxygenspring.domain.rank.dto.RankListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankJoinRepository rankJoinRepository;

    public List<RankListResDto> getRankList(Long groupId) {
        List<RankListResDto> rankList = rankJoinRepository.getRankList(groupId);

        rankList.sort(Comparator.comparing(RankListResDto::getTotalScore));

        rankList.forEach(rank -> rank.setRank(rankList.indexOf(rank) + 1));

        return rankList;
    }
}
