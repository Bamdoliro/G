package com.bamdoliro.gati.domain.chat.exception;

import com.bamdoliro.gati.domain.chat.exception.error.ChatErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class RoomMemberNotFoundException extends GatiException {

    public final static GatiException EXCEPTION = new RoomMemberNotFoundException();

    private RoomMemberNotFoundException() {
        super(ChatErrorProperty.ROOM_MEMBER_NOT_FOUND);
    }
}
