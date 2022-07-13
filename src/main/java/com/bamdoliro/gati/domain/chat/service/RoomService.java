package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomFacade roomFacade;

    @Transactional
    public Room createRoom(String name) {
        return roomRepository.save(
                Room.builder()
                        .name(name)
                        .build()
        );
    }

    @Transactional
    public void updateRoom(Long roomId, String name) {
        Room room = roomFacade.findRoomById(roomId);
        room.updateName(name);
    }
}
