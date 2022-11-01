package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.domain.board.exception.error.BoardErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class LikeOverlapException extends GatiException {

    public static final LikeOverlapException EXCEPTION = new LikeOverlapException();

    private LikeOverlapException() {
        super(BoardErrorProperty.LIKE_OVERLAP);
    }
}
