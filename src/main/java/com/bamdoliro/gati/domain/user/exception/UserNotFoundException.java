package com.bamdoliro.gati.domain.user.exception;

import com.bamdoliro.gati.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class UserNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorProperty.USER_NOT_FOUND);
    }
}
