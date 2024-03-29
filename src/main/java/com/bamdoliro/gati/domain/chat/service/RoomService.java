package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.RoomListResponseDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.RoomResponseDto;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final RoomFacade roomFacade;
    private final UserFacade userFacade;

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

    @Transactional
    public RoomListResponseDto getUserRoom() {
        return new RoomListResponseDto(
                userFacade.getCurrentUser()
                        .getRooms().stream()
                        .map(RoomMember::getRoom)
                        .map(r -> RoomResponseDto.of(r, getRoomNumberOfMembers(r)))
                        .collect(Collectors.toList())
        );
    }

    private int getRoomNumberOfMembers(Room room) {
        return roomMemberRepository.countByRoom(room);
    }

    @Transactional
    public void endRoom(Long roomId) {
        roomFacade.findRoomById(roomId).endRoom();
    }
}
