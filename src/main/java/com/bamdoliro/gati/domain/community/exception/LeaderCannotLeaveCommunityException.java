package com.bamdoliro.gati.domain.community.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class LeaderCannotLeaveCommunityException extends GatiException {

    public final static GatiException EXCEPTION = new LeaderCannotLeaveCommunityException();

    private LeaderCannotLeaveCommunityException() {
        super(ErrorCode.PASSWORD_MISMATCH, "leader 는 community 를 탈퇴할 수 없습니다.");
    }
}
