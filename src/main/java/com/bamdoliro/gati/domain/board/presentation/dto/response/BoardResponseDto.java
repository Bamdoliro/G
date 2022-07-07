package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.Board;
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
    private String writer;
    private int numberOfLikes;

    public static BoardResponseDto of (Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter().getName())
                .numberOfLikes(board.getLikes().size())
                .build();
    }
}
