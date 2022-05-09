package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponse {

    private String writer;
    private String title;
    private String content;
    private BoardType boardType;
}
