package com.bamdoliro.gati.domain.board.exception;

import com.bamdoliro.gati.domain.board.exception.error.BoardErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class ReportOverlapException extends GatiException {

    public static final ReportOverlapException EXCEPTION = new ReportOverlapException();

    private ReportOverlapException() {
        super(BoardErrorProperty.REPORT_OVERLAP);
    }
}
