package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class AlreadyJoinException extends GatiException {

    public static final AlreadyJoinException EXCEPTION = new AlreadyJoinException();

    private AlreadyJoinException() {
        super(ErrorCode.ALREADY_JOIN);
    }
}
