package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class CannotDeleteCommunityException extends GatiException {

    public final static GatiException EXCEPTION = new CannotDeleteCommunityException();

    private CannotDeleteCommunityException() {
        super(ErrorCode.CANNOT_DELETE_COMMUNITY);
    }
}
