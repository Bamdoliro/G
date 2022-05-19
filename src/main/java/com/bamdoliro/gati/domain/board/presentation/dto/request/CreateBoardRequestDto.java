package com.bamdoliro.gati.domain.board.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateBoardRequestDto {

    @NotNull
    private Long communityId;
    @NotNull
    private String title;
    @NotNull
    private String content;
}
