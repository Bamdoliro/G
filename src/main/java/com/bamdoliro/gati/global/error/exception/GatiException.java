package com.bamdoliro.gati.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GatiException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
