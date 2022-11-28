package com.bamdoliro.gati.domain.user.exception;

import com.bamdoliro.gati.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class PasswordMismatchException extends GatiException {

    public final static GatiException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(UserErrorProperty.PASSWORD_MISMATCH);
    }
}
