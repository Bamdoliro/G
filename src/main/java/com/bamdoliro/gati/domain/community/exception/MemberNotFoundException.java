package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class MemberNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new MemberNotFoundException();

    private MemberNotFoundException() {
        super(CommunityErrorProperty.MEMBER_NOT_FOUND);
    }
}
