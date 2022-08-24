package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final String username;
    private final String sentAt;

    public static MessageResponseDto of(Message message, String sentAt) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .username(
                        message.getMessageType() == MessageType.USER ?
                        message.getUser().getName() : null)
                .sentAt(sentAt)
                .build();
    }
}
