package com.bamdoliro.gati.domain.community.presentation.dto.response;

import com.bamdoliro.gati.domain.community.domain.Community;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CommunityDetailResponseDto {

    private Long id;
    private String name;
    private String introduction;
    private int numberOfPeople;
    private int capacity;
    private String code;
    private Boolean isPublic;

    public static CommunityDetailResponseDto of(Community community, int numberOfPeople) {
        return CommunityDetailResponseDto.builder()
                .id(community.getId())
                .name(community.getName())
                .introduction(community.getIntroduction())
                .numberOfPeople(numberOfPeople)
                .capacity(community.getCapacity())
                .code(community.getCode())
                .isPublic(community.getIsPublic())
                .build();
    }
}
