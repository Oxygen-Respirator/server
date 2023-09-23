package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByLangGroupIdAndUsers_UserId(Long groupId, String userId);
}