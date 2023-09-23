package com.oxygen.oxygenspring.db.repository.join;

import com.oxygen.oxygenspring._common.querydsl.QueryDslUtil;
import com.oxygen.oxygenspring.domain.rank.dto.QRankListResDto;
import com.oxygen.oxygenspring.domain.rank.dto.RankListResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.oxygen.oxygenspring.db.entity.QLangGroup.langGroup;
import static com.oxygen.oxygenspring.db.entity.QMessage.message1;
import static com.oxygen.oxygenspring.db.entity.QUsers.users;

@Repository
@RequiredArgsConstructor
public class RankJoinRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public List<RankListResDto> getRankList(Long groupId) {
        return jpaQueryFactory.select(
                        new QRankListResDto(
                                message1.users.userNickname,
                                message1.id.count().intValue(),
                                message1.score.sum()
                        ))
                .from(message1)
                .join(users).on(message1.users.eq(users))
                .join(langGroup).on(message1.langGroup.eq(langGroup))
                .where(langGroup.id.eq(groupId)
                        .and(message1.role.eq("assistant"))
                        .and(message1.isResolve.eq(true))
                )
                .groupBy(users)
                .orderBy(QueryDslUtil.OrderByNull.getDefault())
                .fetch();
    }
}
