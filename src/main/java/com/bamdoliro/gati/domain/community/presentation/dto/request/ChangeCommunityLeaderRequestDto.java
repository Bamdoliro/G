package com.bamdoliro.gati.domain.community.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ChangeCommunityLeaderRequestDto {

    @NotNull
    private Long memberToBeLeaderId;

    @NotNull
    private Long communityId;
}
