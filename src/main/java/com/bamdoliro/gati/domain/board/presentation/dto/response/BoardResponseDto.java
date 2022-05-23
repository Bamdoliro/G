package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class BoardResponseDto {

    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private User writer;
}
