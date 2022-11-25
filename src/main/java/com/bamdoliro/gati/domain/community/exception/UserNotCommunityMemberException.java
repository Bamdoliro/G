package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class UserNotCommunityMemberException extends GatiException {

    public final static GatiException EXCEPTION = new UserNotCommunityMemberException();

    private UserNotCommunityMemberException() {
        super(CommunityErrorProperty.USER_NOT_COMMUNITY_MEMBER);
    }
}
