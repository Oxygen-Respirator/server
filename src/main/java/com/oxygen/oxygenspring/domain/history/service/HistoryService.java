package com.oxygen.oxygenspring.domain.history.service;

import com.oxygen.oxygenspring.db.repository.join.HistoryJoinRepository;
import com.oxygen.oxygenspring.domain.history.dto.HistoryListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryJoinRepository historyJoinRepository;

    public HistoryListResDto getHistoryList(String userId) {

        return historyJoinRepository.getHistoryList(userId);

    }
}
