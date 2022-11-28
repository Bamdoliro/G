package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.domain.ddo.exception.error.DdoErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class AlreadyRecommendException extends GatiException {

    public static final AlreadyRecommendException EXCEPTION = new AlreadyRecommendException();

    private AlreadyRecommendException() {
        super(DdoErrorProperty.ALREADY_JOIN);
    }
}
