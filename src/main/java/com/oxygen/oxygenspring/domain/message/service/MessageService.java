package com.oxygen.oxygenspring.domain.message.service;

import com.oxygen.oxygenspring.db.entity.Message;
import com.oxygen.oxygenspring.db.repository.MessageRepository;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private MessageRepository messageRepository;

    public List<MessageDetailResDto> getDetailMessage(Long groupId) {
        List<Message> groupMessageList = messageRepository.findAllByLangGroupIdAndRoleIn(groupId, List.of("user", "assitant"));

        return groupMessageList.stream()
                .map(entity -> MessageDetailResDto.builder()
                        .role(entity.getRole())
                        .message(entity.getMessage())
                        .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();
    }
}
