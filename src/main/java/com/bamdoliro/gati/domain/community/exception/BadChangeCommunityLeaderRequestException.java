package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class BadChangeCommunityLeaderRequestException extends GatiException {

    public final static GatiException EXCEPTION = new BadChangeCommunityLeaderRequestException();

    private BadChangeCommunityLeaderRequestException() {
        super(ErrorCode.BAD_REQUEST, "해당 멤버에게 Leader 를 위임할 수 없습니다.");
    }
}
