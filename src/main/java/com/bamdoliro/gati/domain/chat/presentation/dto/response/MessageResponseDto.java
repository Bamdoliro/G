package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final User user;
    private final LocalDateTime sentAt;

    public static MessageResponseDto of(Message message) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .user(message.getUser())
                .sentAt(message.getCreatedAt())
                .build();
    }
}
