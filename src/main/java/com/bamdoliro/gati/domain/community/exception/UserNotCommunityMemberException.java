package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class UserNotCommunityMemberException extends GatiException {

    public final static GatiException EXCEPTION = new UserNotCommunityMemberException();

    private UserNotCommunityMemberException() {
        super(ErrorCode.USER_NOT_COMMUNITY_MEMBER);
    }
}
