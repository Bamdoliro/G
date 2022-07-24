package com.bamdoliro.gati.domain.user.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class ExpiredAuthException extends GatiException {

    public final static ExpiredAuthException EXCEPTION = new ExpiredAuthException();

    private ExpiredAuthException() {
        super(ErrorCode.EXPIRED_AUTH);
    }
}
