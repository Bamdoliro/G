package com.bamdoliro.gati.global.error;

import lombok.Getter;

@Getter
public class GatiException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public GatiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public GatiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
