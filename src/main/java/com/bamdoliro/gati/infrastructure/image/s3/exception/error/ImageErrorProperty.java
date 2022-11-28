package com.bamdoliro.gati.infrastructure.image.s3.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageErrorProperty implements ErrorProperty {
    FAILED_SAVE_FILE( 422, "파일 저장에 실패했습니다"),
    EMPTY_FILE( 422, "파일이 비었습니다."),
    TOO_LONG_TITLE(422, "파일명이 너무 깁니다."),
    ;

    private final int status;
    private final String message;
}
