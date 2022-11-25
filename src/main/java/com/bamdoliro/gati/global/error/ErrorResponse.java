package com.bamdoliro.gati.global.error;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    @Builder
    public ErrorResponse(ErrorProperty errorProperty) {
        this.status = errorProperty.getStatus();
        this.message = errorProperty.getMessage();
    }
}
