package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.presentation.dto.request.RoomRequestDto;
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
    public void joinRoom(@PathVariable Long roomId) {
        roomMemberService.joinRoom(roomId);
    }

    @DeleteMapping
    public void leaveRoom(SocketIOClient client, @RequestBody @Valid RoomRequestDto request) {
        roomMemberService.leaveRoom(client, request);
    }
}
