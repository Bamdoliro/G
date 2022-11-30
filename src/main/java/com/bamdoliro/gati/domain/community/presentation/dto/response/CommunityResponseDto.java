package com.bamdoliro.gati.domain.community.presentation.dto.response;

import com.bamdoliro.gati.domain.community.domain.Community;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommunityResponseDto {

    private Long id;
    private String name;
    private String introduction;
    private String backgroundImg;
    private int numberOfPeople;
    private int capacity;
    private String backgroundImage;

    public static CommunityResponseDto of(Community community, int numberOfPeople) {
        return CommunityResponseDto.builder()
                .id(community.getId())
                .name(community.getName())
                .introduction(community.getIntroduction())
                .numberOfPeople(numberOfPeople)
                .backgroundImg(community.getBackgroundImg())
                .capacity(community.getCapacity())
                .backgroundImage(community.getBackgroundImg())
                .build();
    }
}
