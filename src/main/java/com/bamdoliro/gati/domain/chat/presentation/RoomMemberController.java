package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.service.RoomMemberService;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/room/{roomId}/member")
@RequiredArgsConstructor
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @PostMapping
    public void joinRoom(SocketIOClient client, @PathVariable Long roomId) {
        roomMemberService.joinRoom(client, roomId);
    }

    @DeleteMapping
    public void leaveRoom(SocketIOClient client, @PathVariable Long roomId) {
        roomMemberService.leaveRoom(client, roomId);
    }
}
