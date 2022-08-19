package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.chat.facade.RoomMemberFacade;
import com.bamdoliro.gati.domain.chat.presentation.dto.request.RoomRequestDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomMemberService {

    private final RoomMemberRepository roomMemberRepository;
    private final RoomFacade roomFacade;
    private final RoomMemberFacade roomMemberFacade;
    private final UserFacade userFacade;

    @Transactional
    public void joinRoom(SocketIOClient client, RoomRequestDto request) {
        joinRoom(client, roomFacade.findRoomById(Long.valueOf(request.getRoomId())), userFacade.findUserByClient(client));
    }

    @Transactional
    public void joinRoom(SocketIOClient client, Room room, User user) {
        client.joinRoom(String.valueOf(room.getId()));

        roomMemberRepository.save(
                RoomMember.builder()
                        .room(room)
                        .user(user)
                        .build()
        );
    }

    @Transactional
    public void leaveRoom(SocketIOClient client, RoomRequestDto request) {
        client.leaveRoom(request.getRoomId());

        roomMemberRepository.delete(
                roomMemberFacade.findRoomMemberByRoomAndUser(
                        roomFacade.findRoomById(Long.valueOf(request.getRoomId())), userFacade.findUserByClient(client)
                )
        );
    }
}
