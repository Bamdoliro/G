package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.global.utils.CustomDateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final String username;
    private final Long userId;
    private final String sentAt;

    public static MessageResponseDto of(Message message) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .username(
                        message.getMessageType() == MessageType.USER ?
                        message.getUser().getName() : null)
                .userId(message.getMessageType() == MessageType.USER ?
                        message.getUser().getId() : null)
                .sentAt(CustomDateTimeFormatter.formatToDateTime(message.getCreatedAt()))
                .build();
    }
}
