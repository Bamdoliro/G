package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoard {

    @NotNull
    private String writer;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private BoardType boardType;

    public Board createBoardFromCreateBoard() {
        return Board.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .boardType(BoardType.DDO)
                .status(Status.EXISTED)
                .build();
    }
}
