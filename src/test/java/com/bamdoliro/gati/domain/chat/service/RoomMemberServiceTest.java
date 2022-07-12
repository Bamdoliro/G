package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] Room Member")
class RoomMemberServiceTest {

    @InjectMocks
    private RoomMemberService roomMemberService;

    @Mock
    private RoomMemberRepository roomMemberRepository;

    @Mock
    private RoomFacade roomFacade;

    @Mock
    private UserFacade userFacade;

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
        given(roomMemberRepository.save(any())).willReturn(defaultRoomMember);
        given(userFacade.getCurrentUser()).willReturn(defaultUser);
        given(roomFacade.findRoomById(defaultRoom.getId())).willReturn(defaultRoom);
        ArgumentCaptor<RoomMember> captor = ArgumentCaptor.forClass(RoomMember.class);

        // when
        roomMemberService.joinRoom(defaultRoom.getId());

        // then
        verify(roomFacade, times(1)).findRoomById(defaultRoom.getId());
        verify(userFacade, times(1)).getCurrentUser();
        verify(roomMemberRepository, times(1)).save(captor.capture());
        RoomMember savedRoomMember = captor.getValue();
        assertEquals(defaultRoomMember.getRoom(), savedRoomMember.getRoom());
        assertEquals(defaultRoomMember.getUser(), savedRoomMember.getUser());
    }

    @Test
    @DisplayName("[Service] Room 참가 by room and user")
    void givenRoomAndUser_whenJoiningRoom_thenCreatesRoomMember() {
        // given
        given(roomMemberRepository.save(any())).willReturn(defaultRoomMember);
        ArgumentCaptor<RoomMember> captor = ArgumentCaptor.forClass(RoomMember.class);

        // when
        roomMemberService.joinRoom(defaultRoom, defaultUser);

        // then
        verify(roomMemberRepository, times(1)).save(captor.capture());
        RoomMember savedRoomMember = captor.getValue();
        assertEquals(defaultRoomMember.getRoom(), savedRoomMember.getRoom());
        assertEquals(defaultRoomMember.getUser(), savedRoomMember.getUser());
    }
}