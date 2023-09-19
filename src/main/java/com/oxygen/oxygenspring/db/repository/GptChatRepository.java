package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.GptChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptChatRepository extends JpaRepository<GptChat, String> {
}