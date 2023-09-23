package com.oxygen.oxygenspring.domain.lang_group.service;

import com.oxygen.oxygenspring.db.entity.LangGroup;
import com.oxygen.oxygenspring.db.repository.LangGroupRepository;
import com.oxygen.oxygenspring.domain.lang_group.dto.GroupCreateReqDto;
import com.oxygen.oxygenspring.domain.lang_group.dto.GroupListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LangGroupService {
    private final LangGroupRepository langGroupRepository;

    public List<GroupListResDto> getGroupList() {
        List<LangGroup> allEntity = langGroupRepository.findAll();

        return allEntity.stream()
                .map(entity -> GroupListResDto.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build())
                .toList();
    }

    public void createGroup(GroupCreateReqDto reqDto) {
        List<LangGroup> allEntity = reqDto.getGroupList().stream()
                .map(groupName -> LangGroup.builder()
                        .name(groupName)
                        .build())
                .toList();

        langGroupRepository.saveAll(allEntity);
    }


}
