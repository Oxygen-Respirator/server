package com.oxygen.oxygenspring.db.repository.join;

import com.oxygen.oxygenspring.db.entity.QChatMessage;
import com.oxygen.oxygenspring.domain.history.dto.HistoryListResDto;
import com.oxygen.oxygenspring.domain.history.dto.QHistoryListResDto;
import com.oxygen.oxygenspring.domain.rank.dto.RankListResDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

import static com.oxygen.oxygenspring.db.entity.QLangGroup.langGroup;
import static com.oxygen.oxygenspring.db.entity.QUsers.users;

@Repository
@RequiredArgsConstructor
public class HistoryJoinRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final RankJoinRepository rankJoinRepository;

    public HistoryListResDto getHistoryList(String userNickname) {

        List<RankListResDto> rankList = rankJoinRepository.getRankList(null);

        int rankIndex = -1;
        rankList.sort(Comparator.comparing(RankListResDto::getTotalScore));
        for (RankListResDto rank : rankList) {
            if (userNickname.equals(rank.getNickname())) {
                rankIndex = rankList.indexOf(rank) + 1;
                break;
            }
        }


        QChatMessage messageAll = new QChatMessage("messageAll"); // 별칭 messageAll
        QChatMessage messageTrue = new QChatMessage("messageTrue"); // 별칭 messageTrue

        return jpaQueryFactory.select(
                        new QHistoryListResDto(
                                messageTrue.count().intValue(),
                                messageAll.count().intValue(),
                                Expressions.asNumber(rankIndex),
                                messageAll.score.sum().intValue()
                        )
                )
                .from(messageAll)
                .join(users).on(messageAll.users.eq(users))
                .join(langGroup).on(messageAll.langGroup.eq(langGroup))
                .leftJoin(messageTrue).on(messageAll.id.eq(messageTrue.id)
                        .and(messageAll.isResolve.eq(true)))
                .where(users.userNickname.eq(userNickname))
                .groupBy()
                .orderBy()
                .fetchFirst();
    }
}
