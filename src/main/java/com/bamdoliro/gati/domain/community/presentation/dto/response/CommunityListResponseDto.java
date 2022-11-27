package com.bamdoliro.gati.domain.community.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommunityListResponseDto {

    private List<CommunityResponseDto> communityList;
}
