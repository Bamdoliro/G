package com.bamdoliro.gati.domain.ddo.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DdoErrorProperty implements ErrorProperty {
    DDO_NOT_FOUND(404, "뚜를 찾을 수 없습니다."),
    ALREADY_JOIN(422, "이미 참여중입니다."),
    EXCESS_OF_CAPACITY(422, "정원이 초과되었습니다."),

    RECOMMENDATION_NOT_FOUND(404, "추천한적이 없습니다."),
    ALREADY_RECOMMEND(422, "이미 추천중입니다.");

    private final int status;
    private final String message;
}
