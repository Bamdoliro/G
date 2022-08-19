package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.presentation.dto.request.MessageRequestDto;
import com.bamdoliro.gati.domain.chat.service.MessageService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @OnEvent("message")
    public void sendMessage(SocketIOClient client, MessageRequestDto request) {
        messageService.sendUserMessage(client, request);
    }
}
