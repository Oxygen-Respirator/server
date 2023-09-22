package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}