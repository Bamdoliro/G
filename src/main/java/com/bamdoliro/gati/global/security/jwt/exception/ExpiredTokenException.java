package com.bamdoliro.gati.global.security.jwt.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.global.security.jwt.exception.error.JwtErrorProperty;

public class ExpiredTokenException extends GatiException {

    public final static ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(JwtErrorProperty.EXPIRED_TOKEN);
    }
}
