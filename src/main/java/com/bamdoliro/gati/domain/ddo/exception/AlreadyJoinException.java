package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.domain.ddo.exception.error.DdoErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class AlreadyJoinException extends GatiException {

    public static final AlreadyJoinException EXCEPTION = new AlreadyJoinException();

    private AlreadyJoinException() {
        super(DdoErrorProperty.ALREADY_JOIN);
    }
}
