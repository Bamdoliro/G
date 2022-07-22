package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class ExcessOfCapacityException extends GatiException {

    public static final ExcessOfCapacityException EXCEPTION = new ExcessOfCapacityException();

    private ExcessOfCapacityException() {
        super(ErrorCode.EXCESS_OF_CAPACITY);
    }
}
