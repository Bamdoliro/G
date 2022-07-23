package com.bamdoliro.gati.domain.chat.presentation;

import com.bamdoliro.gati.domain.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PutMapping("/{roomId}")
    public void updateRoom(@PathVariable Long roomId, @RequestParam String name) {
        roomService.updateRoom(roomId, name);
    }
}
