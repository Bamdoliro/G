package com.bamdoliro.gati.domain.board.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorProperty implements ErrorProperty {

    BOARD_NOT_FOUND(404,"게시물을 찾을 수 없습니다."),
    LIKE_OVERLAP(422, "좋아요는 한 번만 가능합니다."),
    REPORT_OVERLAP(422, "신고는 한 게시물 당 한 번만 가능합니다.");

    private final int status;
    private final String message;
}
