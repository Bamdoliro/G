package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class AuthorityMismatchException extends GatiException {

    public final static GatiException EXCEPTION = new AuthorityMismatchException();

    private AuthorityMismatchException() {
        super(ErrorCode.AUTHORITY_MISMATCH);
    }
}
