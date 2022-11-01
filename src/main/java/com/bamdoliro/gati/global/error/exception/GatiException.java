package com.bamdoliro.gati.global.error.exception;

import lombok.Getter;

@Getter
public class GatiException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
