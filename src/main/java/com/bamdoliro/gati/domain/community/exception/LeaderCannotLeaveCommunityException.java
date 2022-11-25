package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class LeaderCannotLeaveCommunityException extends GatiException {

    public final static GatiException EXCEPTION = new LeaderCannotLeaveCommunityException();

    private LeaderCannotLeaveCommunityException() {
        super(CommunityErrorProperty.AUTHORITY_MISMATCH);
    }
}
