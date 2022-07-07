package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailDto {

    private String writer;
    private String title;
    private String content;
    private int numberOfLikes;

    public static BoardDetailDto of(Board board) {
        return BoardDetailDto.builder()
                .writer(board.getWriter().getName())
                .title(board.getTitle())
                .content(board.getContent())
                .numberOfLikes(board.getLikes().size())
                .build();
    }
}
