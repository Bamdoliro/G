package com.bamdoliro.gati.domain.community.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateCommunityRequestDto {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 1000)
    private String introduction;

    @NotNull
    private int numberOfPeople;

    @NotNull
    private Boolean isPublic;
}
