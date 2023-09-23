package com.oxygen.oxygenspring.db.repository.join;

import com.oxygen.oxygenspring._common.querydsl.QueryDslUtil;
import com.oxygen.oxygenspring.domain.rank.dto.QRankListResDto;
import com.oxygen.oxygenspring.domain.rank.dto.RankListResDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.oxygen.oxygenspring.db.entity.QChatMessage.chatMessage;
import static com.oxygen.oxygenspring.db.entity.QLangGroup.langGroup;
import static com.oxygen.oxygenspring.db.entity.QUsers.users;

@Repository
@RequiredArgsConstructor
public class RankJoinRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public List<RankListResDto> getRankList(Long groupId) {
        BooleanBuilder builder = new BooleanBuilder();

        if (groupId != null) {
            builder.and(langGroup.id.eq(groupId));
        }

        builder.and(chatMessage.isResolve.eq(true));

        return jpaQueryFactory.select(
                        new QRankListResDto(
                                chatMessage.users.userNickname,
                                chatMessage.id.count().intValue(),
                                chatMessage.score.sum()
                        ))
                .from(chatMessage)
                .join(users).on(chatMessage.users.eq(users))
                .join(langGroup).on(chatMessage.langGroup.eq(langGroup))
                .where(builder)
                .groupBy(users)
                .orderBy(QueryDslUtil.OrderByNull.getDefault())
                .fetch();
    }


}
