package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MessageListResponseDto {

    private List<MessageResponseDto> messageList;
}
