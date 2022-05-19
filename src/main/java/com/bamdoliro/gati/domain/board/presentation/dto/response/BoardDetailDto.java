package com.bamdoliro.gati.domain.board.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardDetailDto {

    private String writer;
    private String title;
    private String content;

    @Builder
    public BoardDetailDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
