package com.oxygen.oxygenspring.domain.lang_group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GroupListResDto {

    private Long id;
    private String name;
}
