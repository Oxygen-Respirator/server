package com.oxygen.oxygenspring.db.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GptChat {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "object", nullable = false, length = 30)
    private String object;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "total_tokens", nullable = false)
    private Integer totalTokens;

    @Column(name = "completion_tokens", nullable = false)
    private Integer completionTokens;

    @Column(name = "prompt_tokens", nullable = false)
    private Integer promptTokens;

    @OneToMany(mappedBy = "gptChat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public GptChat(String id, Users user, String object, String model, Integer totalTokens, Integer completionTokens, Integer promptTokens) {
        this.id = id;
        this.user = user;
        this.object = object;
        this.model = model;
        this.totalTokens = totalTokens;
        this.completionTokens = completionTokens;
        this.promptTokens = promptTokens;
    }

    public void addMessage(Message message) {
        messageList.add(message);
        message.regGptChat(this);
    }
}
