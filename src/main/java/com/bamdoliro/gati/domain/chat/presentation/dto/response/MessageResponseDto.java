package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.domain.user.presentation.dto.response.UserProfileResponse;
import com.bamdoliro.gati.global.utils.CustomDateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final String username;
    private final String sentAt;
    private final Long roomId;
    private final UserProfileResponse userProfile;

    public static MessageResponseDto of(Message message, Long roomId) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .userProfile(message.getMessageType() == MessageType.USER ?
                        UserProfileResponse.of(message.getUser()) : null)
                .sentAt(CustomDateTimeFormatter.formatToDateTime(message.getCreatedAt()))
                .roomId(roomId)
                .build();
    }
}
