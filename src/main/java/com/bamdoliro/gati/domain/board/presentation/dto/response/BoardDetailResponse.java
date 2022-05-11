package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailResponse {

        private String writer;
        private String title;
        private String content;
        private BoardType boardType;
        private int likes;
        private LocalDateTime createdAt;
}

