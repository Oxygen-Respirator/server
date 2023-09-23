package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByLangGroupIdAndRoleIn(Long groupId, List<String> role);
}