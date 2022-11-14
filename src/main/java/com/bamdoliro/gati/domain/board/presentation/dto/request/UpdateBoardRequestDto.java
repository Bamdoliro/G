package com.bamdoliro.gati.domain.board.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Builder
public class UpdateBoardRequestDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @Size(min = 10, max = 4000)
    private String content;
}
