package com.bamdoliro.gati.domain.chat.facade;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.exception.RoomMemberNotFoundException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMemberFacade {

    private final RoomMemberRepository roomMemberRepository;

    public RoomMember findRoomMemberByRoomAndUser(Room room, User user) {
        return roomMemberRepository.findByRoomAndUser(room, user)
                .orElseThrow(() -> RoomMemberNotFoundException.EXCEPTION);
    }
}
