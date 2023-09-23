package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.TimestampedOnlyCreated;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Message extends TimestampedOnlyCreated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "score")
    private Integer score;

    @Column(name = "is_resolve")
    private Boolean isResolve;

    @ManyToOne(fetch = FetchType.LAZY)
    private LangGroup langGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Builder
    public Message(String role, String message, Integer score, Boolean isResolve, LangGroup langGroup, Users users) {
        this.role = role;
        this.message = message;
        this.score = score;
        this.isResolve = isResolve;
        this.langGroup = langGroup;
        this.users = users;
    }
}
