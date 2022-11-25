package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class CannotDeleteCommunityException extends GatiException {

    public final static GatiException EXCEPTION = new CannotDeleteCommunityException();

    private CannotDeleteCommunityException() {
        super(CommunityErrorProperty.CANNOT_DELETE_COMMUNITY);
    }
}
