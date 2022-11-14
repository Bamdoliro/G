package com.bamdoliro.gati.global.security.jwt.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.global.security.jwt.exception.error.JwtErrorProperty;

public class InvalidTokenException extends GatiException {

    public final static InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(JwtErrorProperty.INVALID_TOKEN);
    }
}
