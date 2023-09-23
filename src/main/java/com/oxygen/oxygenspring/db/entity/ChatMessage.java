package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.TimestampedOnlyCreated;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage extends TimestampedOnlyCreated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_message")
    private String userMessage;

    @Column(name = "score")
    private Integer score;

    @Column(name = "answer")
    private String answer;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "tail_question")
    private String tailQuestion;

    @Column(name = "etc")
    private String etc;

    @Column(name = "is_resolve")
    private Boolean isResolve;

    @ManyToOne(fetch = FetchType.LAZY)
    private LangGroup langGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;


    @Builder
    public ChatMessage(String userMessage, Integer score, String answer, String keyword, String tailQuestion, String etc, Boolean isResolve, LangGroup langGroup, Users users) {
        this.answer = answer;
        this.keyword = keyword;
        this.tailQuestion = tailQuestion;
        this.userMessage = userMessage;
        this.score = score;
        this.etc = etc;
        this.isResolve = isResolve;
        this.langGroup = langGroup;
        this.users = users;
    }
}
