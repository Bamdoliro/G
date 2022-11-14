package com.bamdoliro.gati.domain.chat.exception;

import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class RoomNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new RoomNotFoundException();

    private RoomNotFoundException() {
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}
