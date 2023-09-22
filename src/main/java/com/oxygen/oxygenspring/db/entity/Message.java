package com.oxygen.oxygenspring.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index", nullable = false)
    private Integer index;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "finish_reason", nullable = false, length = 30)
    private String finishReason;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GptChat gptChat;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Message(Integer index, String message, String finishReason, GptChat gptChat) {
        this.index = index;
        this.message = message;
        this.finishReason = finishReason;
        gptChat.addMessage(this);
    }

    public void regGptChat(GptChat gptChat) {
        this.gptChat = gptChat;
    }
}
