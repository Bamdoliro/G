package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BoardResponseDto {

    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private User writer;

    @Builder
    public BoardResponseDto(String title, String content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
