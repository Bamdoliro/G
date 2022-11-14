package com.bamdoliro.gati.domain.chat.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatErrorProperty implements ErrorProperty {
    ROOM_NOT_FOUND(404, "채팅방을 찾을 수 없습니다."),
    ROOM_MEMBER_NOT_FOUND(404, "채팅방 멤버를 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
