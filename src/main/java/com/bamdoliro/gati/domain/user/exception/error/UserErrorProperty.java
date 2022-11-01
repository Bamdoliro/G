package com.bamdoliro.gati.domain.user.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorProperty implements ErrorProperty {

    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다.");

    private final int status;
    private final String message;
}
