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
    private String backgroundImg;
    private int capacity;
    private String code;
    private Boolean isPublic;
    private List<String> members;

    public static CommunityDetailResponseDto of(Community community) {
        return CommunityDetailResponseDto.builder()
                .id(community.getId())
                .name(community.getName())
                .introduction(community.getIntroduction())
                .backgroundImg(community.getBackgroundImg())
                .capacity(community.getCapacity())
                .code(community.getCode())
                .isPublic(community.getIsPublic())
                .members(community.getMembers().stream()
                        .map(m -> m.getUser().getName()).collect(Collectors.toList()))
                .build();
    }
}
