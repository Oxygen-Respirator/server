package com.oxygen.oxygenspring.domain.lang_group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GroupCreateReqDto {
    private List<String> groupList;
}
