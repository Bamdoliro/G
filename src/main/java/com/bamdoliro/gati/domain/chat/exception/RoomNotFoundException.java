package com.bamdoliro.gati.domain.chat.exception;

import com.bamdoliro.gati.domain.chat.exception.error.ChatErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class RoomNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new RoomNotFoundException();

    private RoomNotFoundException() {
        super(ChatErrorProperty.ROOM_NOT_FOUND);
    }
}
