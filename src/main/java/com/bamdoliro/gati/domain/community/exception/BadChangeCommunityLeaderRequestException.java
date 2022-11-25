package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class BadChangeCommunityLeaderRequestException extends GatiException {

    public final static GatiException EXCEPTION = new BadChangeCommunityLeaderRequestException();

    private BadChangeCommunityLeaderRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
