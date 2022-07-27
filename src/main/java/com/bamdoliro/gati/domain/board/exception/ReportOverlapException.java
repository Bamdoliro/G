package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class ReportOverlapException extends GatiException {

    public static final ReportOverlapException EXCEPTION = new ReportOverlapException();

    private ReportOverlapException() {
        super(ErrorCode.REPORT_OVERLAP);
    }
}
