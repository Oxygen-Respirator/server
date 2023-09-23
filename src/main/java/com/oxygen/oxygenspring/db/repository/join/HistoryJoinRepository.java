package com.oxygen.oxygenspring.db.repository.join;

import com.oxygen.oxygenspring.domain.history.dto.HistoryListResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoryJoinRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final RankJoinRepository rankJoinRepository;

    public HistoryListResDto getHistoryList(String userId) {
//        QMessage messageAll = new QMessage("messageAll"); // 별칭 messageAll
//        QMessage messageTrue = new QMessage("messageTrue"); // 별칭 messageTrue
//
//        return jpaQueryFactory.select(
//                        new QHistoryListResDto(
//                                messageTrue.count(),
//                                messageAll.count(),
//                                ,
//                                messageAll.score.sum()
//                        )
//                )
//                .from(messageAll)
//                .join(users).on(messageAll.users.eq(users))
//                .join(langGroup).on(messageAll.langGroup.eq(langGroup))
//                .leftJoin(messageTrue).on(messageAll.id.eq(messageTrue.id)
//                        .and(messageAll.isResolve.eq(true)))
//                .where(users.userId.eq(userId))
//                .groupBy()
//                .orderBy()
//                .fetch();

        return null;
    }
}
