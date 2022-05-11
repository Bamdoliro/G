package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponse {

    private String writer;
    private String title;
    private BoardType boardType;
    private LocalDateTime createdAt;
}
