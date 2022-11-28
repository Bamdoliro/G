package com.bamdoliro.gati.domain.user.exception;

import com.bamdoliro.gati.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class UserAlreadyExistsException extends GatiException {

    public final static UserAlreadyExistsException EXCEPTION = new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(UserErrorProperty.USER_ALREADY_EXISTS);
    }
}
