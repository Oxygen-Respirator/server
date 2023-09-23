package com.oxygen.oxygenspring.domain.message.service;

import com.oxygen.oxygenspring.db.entity.LangGroup;
import com.oxygen.oxygenspring.db.entity.Message;
import com.oxygen.oxygenspring.db.entity.Users;
import com.oxygen.oxygenspring.db.repository.MessageRepository;
import com.oxygen.oxygenspring.domain.message.dto.MessageCreateReqDto;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import com.oxygen.oxygenspring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<MessageDetailResDto> getDetailMessage(Long groupId) {
        List<Message> groupMessageList = messageRepository.findAllByLangGroupIdAndRoleIn(groupId, List.of("user", "assistant"));

        return groupMessageList.stream()
                .map(entity -> MessageDetailResDto.builder()
                        .id(entity.getId())
                        .score(entity.getScore())
                        .isResolve(entity.getIsResolve())
                        .langGroupName(entity.getLangGroup().getName())
                        .role(entity.getRole())
                        .message(entity.getMessage())
                        .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();
    }

    public MessageDetailResDto createMessage(User userDetails, Long groupId, MessageCreateReqDto reqDto) {
        Users user = userService.getUser(userDetails.getUsername());
        Random random = new Random();

        int ran = random.nextInt(101);
        boolean isResolve = true;
        if (ran < 51) isResolve = false;

        Message message = Message.builder()
                .langGroup(new LangGroup(groupId))
                .role("user")
                .message(reqDto.getMessage())
                .score(null)
                .isResolve(null)
                .users(user)
                .build();

        messageRepository.saveAndFlush((message));

        Message resMessage = Message.builder()
                .langGroup(new LangGroup(groupId))
                .role("assistant")
                .message("지금은 GPT가 연결되지 않았어용\n 랜덤으로 점수를 주고 50점 이하는 다시풀기 플래그가 활성화 됩니당")
                .score(ran)
                .isResolve(isResolve)
                .users(user)
                .build();

        messageRepository.saveAndFlush(resMessage);

        return MessageDetailResDto.builder()
                .id(resMessage.getId())
                .score(resMessage.getScore())
                .isResolve(resMessage.getIsResolve())
                .langGroupName(resMessage.getLangGroup().getName())
                .role(resMessage.getRole())
                .message(resMessage.getMessage())
                .createdAt(resMessage.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
