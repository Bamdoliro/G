package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class BoardNotFoundException extends GatiException {
    public final static GatiException EXCEPTION = new BoardNotFoundException();

    private BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}
