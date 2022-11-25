package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.domain.community.exception.error.CommunityErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class CommunityNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new CommunityNotFoundException();

    private CommunityNotFoundException() {
        super(CommunityErrorProperty.COMMUNITY_NOT_FOUND);
    }
}
