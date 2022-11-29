package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.presentation.dto.request.MessageRequestDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.MessageListResponseDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.MessageResponseDto;
import com.bamdoliro.gati.domain.chat.service.MessageService;
import com.bamdoliro.gati.global.socket.SocketEventProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat/{roomId}")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @OnEvent(SocketEventProperty.MESSAGE_KEY)
    public void sendMessage(SocketIOClient client, MessageRequestDto request) {
        messageService.sendUserMessage(client, request);
    }

    @GetMapping
    public MessageListResponseDto getLastMessage(@PathVariable Long roomId, Pageable pageable) {
        return messageService.getLastMessage(roomId, pageable);
    }
}
