package com.oxygen.oxygenspring.domain.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring._common.utils.Utils;
import com.oxygen.oxygenspring.db.entity.ChatMessage;
import com.oxygen.oxygenspring.db.entity.LangGroup;
import com.oxygen.oxygenspring.db.entity.Users;
import com.oxygen.oxygenspring.db.repository.LangGroupRepository;
import com.oxygen.oxygenspring.db.repository.MessageRepository;
import com.oxygen.oxygenspring.domain.message.dto.MessageCreateReqDto;
import com.oxygen.oxygenspring.domain.message.dto.MessageDetailResDto;
import com.oxygen.oxygenspring.domain.message.dto.kafka.KafkaReqDto;
import com.oxygen.oxygenspring.domain.message.dto.kafka.KafkaResDto;
import com.oxygen.oxygenspring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final LangGroupRepository langGroupRepository;

    private final UserService userService;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageConsumer messageConsumer;
    private final ObjectMapper objectMapper;

    public List<MessageDetailResDto> getDetailMessage(Long groupId, String userId) {
        List<ChatMessage> groupChatMessageList;
        if (groupId != null) {
            groupChatMessageList = messageRepository.findAllByLangGroupIdAndUsers_UserId(groupId, userId);
        } else {
            groupChatMessageList = messageRepository.findAllByUsers_UserId(userId);
        }

        return groupChatMessageList.stream()
                .map(entity -> MessageDetailResDto.builder()
                        .id(entity.getId())
                        .score(entity.getScore())
                        .isResolve(entity.getIsResolve())
                        .langGroupName(entity.getLangGroup().getName())
                        .userMessage(entity.getUserMessage())
                        .answer(entity.getAnswer())
                        .keyword(entity.getKeyword())
                        .tailQuestion(entity.getTailQuestion())
                        .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();
    }

    public MessageDetailResDto createMessage(User userDetails, Long groupId, MessageCreateReqDto reqDto) {
        Users user = userService.getUser(userDetails.getUsername());

        if (user.getRemainAnswerCount() <= 0) {
            throw new ApiException(ResponseCode.REQUIRED_ANSWER_COUNT);
        }

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        messageConsumer.register(correlationId, responseFuture);

        LangGroup langGroup = langGroupRepository.findById(groupId)
                .orElseThrow(() -> new ApiException(ResponseCode.RESOURCE_NOT_FOUND, "해당 그룹이 존재하지 않습니다. id : " + groupId));


        Long entityCnt = messageRepository.countByLangGroupIdAndUsers_UserId(groupId, user.getUserId());
        KafkaReqDto kafkaReqDto = KafkaReqDto.builder()
                .lang(langGroup.getName())
                .message(reqDto.getMessage())
                .isFirst(entityCnt == 0)
                .build();

        String request = Utils.objectToJson(kafkaReqDto, objectMapper);

        org.springframework.messaging.Message<String> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "request_chat")
                .setHeader(KafkaHeaders.GROUP_ID, "gpt")
                .setHeader("correlationId", correlationId)
                .build();

        try {
            kafkaTemplate.send(message).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(ResponseCode.KAFKA_ERROR);
        } catch (ExecutionException e) {
            throw new ApiException(ResponseCode.KAFKA_ERROR);
        }

        String response;
        try {
            response = Utils.unicodeDecode(responseFuture.get(100, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(ResponseCode.KAFKA_ERROR, "카프카 에러가 발생했습니다.");
        } catch (ExecutionException e) {
            throw new ApiException(ResponseCode.KAFKA_ERROR, "카프카 에러가 발생했습니다.");
        } catch (TimeoutException e) {
            throw new ApiException(ResponseCode.KAFKA_ERROR, "GPT Connector에서 응답이 오지 않습니다.");
        }

        KafkaResDto kafkaResDto = Utils.jsonToObject(response, KafkaResDto.class, objectMapper);


        int score = Integer.parseInt(kafkaResDto.getScore());
        ChatMessage resChatMessageEntity = ChatMessage.builder()
                .langGroup(langGroup)
                .userMessage(reqDto.getMessage())
                .score(score)
                .answer(kafkaResDto.getAnswer())
                .keyword(kafkaResDto.getKeyword())
                .tailQuestion(kafkaResDto.getTailQuestion())
                .etc(kafkaResDto.getEtc())
                .isResolve(score > 50)
                .users(user)
                .build();

        messageRepository.saveAndFlush(resChatMessageEntity);

        user.minusRemainAnswerCount();

        return MessageDetailResDto.builder()
                .id(resChatMessageEntity.getId())
                .score(resChatMessageEntity.getScore())
                .isResolve(resChatMessageEntity.getIsResolve())
                .langGroupName(resChatMessageEntity.getLangGroup().getName())
                .userMessage(resChatMessageEntity.getUserMessage())
                .answer(resChatMessageEntity.getAnswer())
                .keyword(resChatMessageEntity.getKeyword())
                .tailQuestion(resChatMessageEntity.getTailQuestion())
                .createdAt(resChatMessageEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
