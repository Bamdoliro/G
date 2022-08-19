package com.bamdoliro.gati.domain.chat.presentation.dto.request;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MessageRequestDto {

    @NotNull
    private String message;

    @NotNull
    private MessageType messageType;

    @NotNull
    private String roomId;

    public Message toEntity(User user, Room room) {
        return Message.builder()
                .message(message)
                .messageType(messageType)
                .user(user)
                .room(room)
                .build();
    }
}
