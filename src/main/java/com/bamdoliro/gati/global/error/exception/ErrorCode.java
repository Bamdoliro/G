package com.bamdoliro.gati.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorProperty {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),

    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),





    DDO_NOT_FOUND(404, "뚜를 찾을 수 없습니다."),
    ALREADY_JOIN(422, "이미 참여중입니다."),
    EXCESS_OF_CAPACITY(422, "정원이 초과되었습니다."),

    RECOMMENDATION_NOT_FOUND(404, "추천한적이 없습니다."),
    ALREADY_RECOMMEND(422, "이미 추천중입니다."),
    ;

    private final int status;
    private final String message;
}
