package com.bamdoliro.gati.domain.user.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.global.error.exception.ErrorCode;

public class PasswordMismatchException extends GatiException {

    public final static GatiException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
