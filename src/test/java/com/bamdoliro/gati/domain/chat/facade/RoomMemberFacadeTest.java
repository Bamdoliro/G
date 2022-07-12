package com.bamdoliro.gati.domain.chat.facade;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.exception.RoomMemberNotFoundException;
import com.bamdoliro.gati.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Facade] Room Member")
class RoomMemberFacadeTest {

    @InjectMocks
    private RoomMemberFacade roomMemberFacade;

    @Mock
    private RoomMemberRepository roomMemberRepository;

    private final Room defaultRoom = Room.builder()
            .name("귀요미팟")
            .build();

    private final User defaultUser = User.builder()
            .name("김가티")
            .email("gati@bamdoliro.com")
            .build();

    private final RoomMember defaultRoomMember = RoomMember.builder()
            .room(defaultRoom)
            .user(defaultUser)
            .build();

    @Test
    @DisplayName("[Facade] Room, User로 Member 조회")
    void givenRoomAndUser_whenFindingRoomMember_thenReturnsRoomMember() {
        // given
        given(roomMemberRepository.findByRoomAndUser(defaultRoom, defaultUser)).willReturn(Optional.of(defaultRoomMember));

        // when
        RoomMember foundRoomMember = roomMemberFacade.findRoomMemberByRoomAndUser(defaultRoom, defaultUser);

        // then
        verify(roomMemberRepository, times(1)).findByRoomAndUser(defaultRoom, defaultUser);
        assertEquals(foundRoomMember.getRoom(), defaultRoom);
        assertEquals(foundRoomMember.getUser(), defaultUser);
    }

    @Test
    @DisplayName("[Facade] Room, User로 Member 조회 - invalid한 경우")
    void givenInvalidRoomAndUser_whenFindingRoomMember_thenThrowsRoomMemberNotFoundException() {
        // given
        given(roomMemberRepository.findByRoomAndUser(defaultRoom, defaultUser)).willReturn(Optional.empty());

        // when and then
        assertThrows(RoomMemberNotFoundException.class,
                () -> roomMemberFacade.findRoomMemberByRoomAndUser(defaultRoom, defaultUser));
        verify(roomMemberRepository, times(1)).findByRoomAndUser(defaultRoom, defaultUser);
    }
}