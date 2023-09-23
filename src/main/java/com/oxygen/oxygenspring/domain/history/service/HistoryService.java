package com.oxygen.oxygenspring.domain.history.service;

import com.oxygen.oxygenspring.db.entity.Users;
import com.oxygen.oxygenspring.db.repository.join.HistoryJoinRepository;
import com.oxygen.oxygenspring.domain.history.dto.HistoryListResDto;
import com.oxygen.oxygenspring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryJoinRepository historyJoinRepository;
    private final UserService userService;

    public HistoryListResDto getHistoryList(String userId) {
        Users user = userService.getUser(userId);

        return historyJoinRepository.getHistoryList(user.getUserNickname());

    }
}
