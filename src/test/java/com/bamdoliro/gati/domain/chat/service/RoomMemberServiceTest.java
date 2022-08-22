package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.chat.facade.RoomMemberFacade;
import com.bamdoliro.gati.domain.chat.presentation.dto.request.MessageRequestDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.request.RoomRequestDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.corundumstudio.socketio.SocketIOClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] Room Member")
class RoomMemberServiceTest {

    @InjectMocks private RoomMemberService roomMemberService;

    @Mock private RoomMemberRepository roomMemberRepository;
    @Mock private RoomFacade roomFacade;
    @Mock private RoomMemberFacade roomMemberFacade;
    @Mock private UserFacade userFacade;
    @Mock private SocketIOClient client;
    @Mock private MessageService messageService;

    private final User defaultUser = User.builder()
            .name("김가티")
            .email("gati@bamdoliro.com")
            .build();

    private final Room defaultRoom = Room.builder()
            .name("귀요미팟")
            .build();

    private final RoomMember defaultRoomMember = RoomMember.builder()
            .room(defaultRoom)
            .user(defaultUser)
            .build();

    @Test
    @DisplayName("[Service] Room 참가 by roomId")
    void givenRoomId_whenJoiningRoom_thenCreatesRoomMember() {
        // given
        given(userFacade.findUserByClient(any(SocketIOClient.class))).willReturn(defaultUser);
        given(roomFacade.findRoomById(anyLong())).willReturn(defaultRoom);

        // when
        roomMemberService.joinRoom(client, new RoomRequestDto(String.valueOf(1L)));

        // then
        verify(roomFacade, times(1)).findRoomById(1L);
        verify(userFacade, times(1)).findUserByClient(client);
    }

    @Test
    @DisplayName("[Service] Room 참가 by room and user")
    void givenRoomAndUser_whenJoiningRoom_thenCreatesRoomMember() {
        // given
        willDoNothing().given(client).joinRoom(anyString());
        willDoNothing().given(messageService).sendSystemMessage(any(MessageRequestDto.class));
        given(roomMemberRepository.save(any())).willReturn(defaultRoomMember);
        ArgumentCaptor<RoomMember> captor = ArgumentCaptor.forClass(RoomMember.class);

        // when
        roomMemberService.joinRoom(client, defaultRoom, defaultUser);

        // then
        verify(client, times(1)).joinRoom(String.valueOf(defaultRoom.getId()));
        verify(messageService, times(1)).sendSystemMessage(any(MessageRequestDto.class));
        verify(roomMemberRepository, times(1)).save(captor.capture());
        RoomMember savedRoomMember = captor.getValue();
        assertEquals(defaultRoomMember.getRoom(), savedRoomMember.getRoom());
        assertEquals(defaultRoomMember.getUser(), savedRoomMember.getUser());
    }

    @Test
    @DisplayName("[Service] Room 나가기")
    void givenRoomId_whenLeavingRoom_thenDeletesRoomMember() {
        // given
        given(roomFacade.findRoomById(anyLong())).willReturn(defaultRoom);
        given(userFacade.findUserByClient(any(SocketIOClient.class))).willReturn(defaultUser);
        given(roomMemberFacade.findRoomMemberByRoomAndUser(defaultRoom, defaultUser)).willReturn(defaultRoomMember);
        willDoNothing().given(roomMemberRepository).delete(defaultRoomMember);
        willDoNothing().given(messageService).sendSystemMessage(any(MessageRequestDto.class));
        willDoNothing().given(client).leaveRoom(anyString());

        // when
        roomMemberService.leaveRoom(client, new RoomRequestDto(String.valueOf(1L)));

        // then
        verify(roomFacade, times(1)).findRoomById(any());
        verify(userFacade, times(1)).findUserByClient(client);
        verify(roomMemberFacade, times(1)).findRoomMemberByRoomAndUser(defaultRoom, defaultUser);
        verify(roomMemberRepository, times(1)).delete(defaultRoomMember);
        verify(messageService, times(1)).sendSystemMessage(any(MessageRequestDto.class));
        verify(client, times(1)).leaveRoom(String.valueOf(1L));
    }
}