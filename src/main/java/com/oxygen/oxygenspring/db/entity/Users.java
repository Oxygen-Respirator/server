package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "remain_answer_count")
    private Integer remainAnswerCount;

    @Column(name = "max_answer_count")
    private Integer maxAnswerCount;

    @Builder
    public Users(String userId, String userPw, String userNickname, Integer maxAnswerCount) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNickname = userNickname;
        this.remainAnswerCount = maxAnswerCount;
        this.maxAnswerCount = maxAnswerCount;
    }

    public void minusRemainAnswerCount() {
        this.remainAnswerCount--;
    }
}
