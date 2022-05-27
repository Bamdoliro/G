package com.bamdoliro.gati.domain.community.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class JoinCommunityRequestDto {

    @NotNull
    private Long communityId;

    @Nullable
    @Size(min = 4, max = 4)
    private String password;

}
