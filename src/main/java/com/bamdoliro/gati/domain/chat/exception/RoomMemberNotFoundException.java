package com.bamdoliro.gati.domain.chat.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class RoomMemberNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new RoomMemberNotFoundException();

    private RoomMemberNotFoundException() {
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}
