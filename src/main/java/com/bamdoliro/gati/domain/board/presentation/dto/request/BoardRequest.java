package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardRequest {

    @NotNull
    private String writer;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private BoardType boardType;
}
