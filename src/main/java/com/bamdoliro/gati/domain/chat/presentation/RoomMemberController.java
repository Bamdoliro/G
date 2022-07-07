package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/room/member")
@RequiredArgsConstructor
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @PostMapping("/join")
    public void joinRoom(@RequestParam Long roomId) {
        roomMemberService.joinRoom(roomId);
    }
}
