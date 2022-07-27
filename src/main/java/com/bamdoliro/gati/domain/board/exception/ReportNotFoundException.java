package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class ReportNotFoundException extends GatiException {
    public final static ReportNotFoundException EXCEPTION = new ReportNotFoundException();

    private ReportNotFoundException() {
        super(ErrorCode.BAD_REQUEST);
    }

}
