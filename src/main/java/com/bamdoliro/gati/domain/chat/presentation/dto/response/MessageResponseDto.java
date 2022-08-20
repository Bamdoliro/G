package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final String username;

//    TODO :: sentAt 개버그 수정
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private final LocalDateTime sentAt;

    public static MessageResponseDto of(Message message) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .username(message.getUser().getName())
//                .sentAt(message.getCreatedAt())
                .build();
    }
}
