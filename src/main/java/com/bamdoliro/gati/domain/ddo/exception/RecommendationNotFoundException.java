package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class RecommendationNotFoundException extends GatiException {

    public static final RecommendationNotFoundException EXCEPTION = new RecommendationNotFoundException();

    private RecommendationNotFoundException() {
        super(ErrorCode.RECOMMENDATION_NOT_FOUND);
    }
}
