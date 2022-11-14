package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class LikeOverlapException extends GatiException {

    public static final LikeOverlapException EXCEPTION = new LikeOverlapException();

    private LikeOverlapException() {
        super(ErrorCode.LIKE_OVERLAP);
    }
}
