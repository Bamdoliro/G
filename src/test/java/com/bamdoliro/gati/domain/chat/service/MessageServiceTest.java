package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.chat.domain.repository.MessageRepository;
import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.chat.presentation.dto.request.MessageRequestDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.MessageResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] Message")
class MessageServiceTest {

    @InjectMocks @Spy
    private MessageService messageService;

    @Mock private RoomFacade roomFacade;
    @Mock private UserFacade userFacade;
    @Mock private SocketIOServer server;
    @Mock private SocketIOClient client;
    @Mock private BroadcastOperations broadcastOperations;
    @Mock private MessageRepository messageRepository;
    @Mock private ObjectMapper mapper;

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

    private final Message defaultMessage = Message.builder()
            .message("야머해")
            .messageType(MessageType.USER)
            .room(defaultRoom)
            .user(defaultUser)
            .build();

    @Test
    @DisplayName("[Service] sendUserMessage")
    void givenMessageRequestDto_whenSendingUserMessage_thenSends() {
        // given
        MessageRequestDto request = new MessageRequestDto("안뇽", MessageType.USER, "1");
        given(userFacade.findUserByClient(client)).willReturn(defaultUser);
        given(roomFacade.findRoomById(Long.valueOf(request.getRoomId()))).willReturn(defaultRoom);
        willDoNothing().given(messageService).sendMessage(request, defaultUser, defaultRoom);

        // when
        messageService.sendUserMessage(client, request);

        // then
        verify(userFacade, times(1)).findUserByClient(client);
        verify(roomFacade, times(1)).findRoomById(Long.valueOf(request.getRoomId()));
        verify(messageService, times(1)).sendMessage(request, defaultUser, defaultRoom);
    }

    @Test
    @DisplayName("[Service] sendSystemMessage")
    void givenMessageRequestDto_whenSendingSystemMessage_thenSends() {
        // given
        MessageRequestDto request = new MessageRequestDto("나 님이 참가함ㅋㅋ", MessageType.SYSTEM, "1");
        given(roomFacade.findRoomById(Long.valueOf(request.getRoomId()))).willReturn(defaultRoom);
        willDoNothing().given(messageService).sendMessage(request, null, defaultRoom);

        // when
        messageService.sendSystemMessage(request);

        // then
        verify(roomFacade, times(1)).findRoomById(Long.valueOf(request.getRoomId()));
        verify(messageService, times(1)).sendMessage(request, null, defaultRoom);
    }

    @Test
    @DisplayName("[Service] sendMessage")
    void givenMessageRequestDtoAndUserAndRoom_whenSendingMessage_thenSends() throws JsonProcessingException {
        // given
        MessageRequestDto request = new MessageRequestDto("야머해", MessageType.USER, "1");
        given(server.getRoomOperations(request.getRoomId())).willReturn(broadcastOperations);
        given(messageRepository.save(any(Message.class))).willReturn(defaultMessage);
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        given(mapper.writeValueAsString(any())).willReturn("2022-02-02 02:02");
        willDoNothing().given(broadcastOperations).sendEvent(anyString(), any(MessageResponseDto.class));

        // when
        messageService.sendMessage(request, defaultUser, defaultRoom);

        // then
        verify(server, times(1)).getRoomOperations(request.getRoomId());
        verify(messageRepository, times(1)).save(captor.capture());
        Message savedMessage = captor.getValue();
        assertEquals(defaultMessage.getMessage(), savedMessage.getMessage());
        assertEquals(defaultMessage.getMessageType(), savedMessage.getMessageType());
        assertEquals(defaultMessage.getUser(), savedMessage.getUser());
        assertEquals(defaultMessage.getRoom(), savedMessage.getRoom());
        verify(broadcastOperations, times(1)).sendEvent(anyString(), any(MessageResponseDto.class));
    }
}