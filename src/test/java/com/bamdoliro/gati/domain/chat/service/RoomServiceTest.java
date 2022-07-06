package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.ChatMember;
import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] Chat Room")
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    private final Room defaultRoom = Room.builder()
            .name("귀요미팟")
            .build();

    @DisplayName("[Service] Chat Room 생성")
    @Test
    void givenRoomName_whenCreatingRoom_thenCreatesRoom() {
        // given
        given(roomRepository.save(any())).willReturn(defaultRoom);
        ArgumentCaptor<Room> captor = ArgumentCaptor.forClass(Room.class);

        // when
        roomService.createRoom("귀요미팟");

        // then
        verify(roomRepository, times(1)).save(captor.capture());
        Room savedRoom = captor.getValue();
        assertEquals("귀요미팟", savedRoom.getName());
        assertEquals(new ArrayList<ChatMember>(), savedRoom.getChatMembers());
        assertEquals(new ArrayList<Message>(), savedRoom.getMessages());
    }
}