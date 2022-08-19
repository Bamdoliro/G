package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.presentation.dto.request.RoomRequestDto;
import com.bamdoliro.gati.domain.chat.service.RoomMemberService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @OnEvent("join-room")
    public void joinRoom(SocketIOClient client, @RequestBody @Valid RoomRequestDto request) {
        roomMemberService.joinRoom(client, request);
    }

    @OnEvent("leave-room")
    public void leaveRoom(SocketIOClient client, @RequestBody @Valid RoomRequestDto request) {
        roomMemberService.leaveRoom(client, request);
    }
}
