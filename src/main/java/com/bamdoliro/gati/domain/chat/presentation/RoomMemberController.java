package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/room/member")
@RequiredArgsConstructor
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @PostMapping("/join/{roomId}")
    public void joinRoom(@PathVariable Long roomId) {
        roomMemberService.joinRoom(roomId);
    }
}
