package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class BadCreateCommunityRequestException extends GatiException {

    public final static GatiException EXCEPTION = new BadCreateCommunityRequestException();

    private BadCreateCommunityRequestException() {
        super(ErrorCode.BAD_REQUEST, "private 공동체는 비밀번호를 설정해야 합니다.");
    }
}
