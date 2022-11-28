package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class AuthorityMismatchException extends GatiException {

    public final static GatiException EXCEPTION = new AuthorityMismatchException();

    private AuthorityMismatchException() {
        super(CommunityErrorProperty.AUTHORITY_MISMATCH);
    }
}
