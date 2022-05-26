package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class CommunityPasswordMismatchException extends GatiException {

    public final static GatiException EXCEPTION = new CommunityPasswordMismatchException();

    private CommunityPasswordMismatchException() {
        super(ErrorCode.COMMUNITY_NOT_FOUND);
    }
}
