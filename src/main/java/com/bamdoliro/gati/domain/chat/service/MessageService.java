package com.bamdoliro.gati.domain.chat.service;

import com.bamdoliro.gati.domain.chat.domain.Message;
import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.repository.MessageRepository;
import com.bamdoliro.gati.domain.chat.facade.RoomFacade;
import com.bamdoliro.gati.domain.chat.presentation.dto.request.MessageRequestDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.MessageListResponseDto;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.MessageResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.global.socket.SocketEventProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SocketIOServer server;
    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final MessageRepository messageRepository;

    public void sendUserMessage(SocketIOClient client, MessageRequestDto request) {
        sendMessage(request, userFacade.findUserByClient(client),
                roomFacade.findRoomById(Long.valueOf(request.getRoomId())));
    }

    public void sendSystemMessage(MessageRequestDto request) {
        sendMessage(request, null,
                roomFacade.findRoomById(Long.valueOf(request.getRoomId())));
    }

    @Transactional
    public void sendMessage(MessageRequestDto request, User user, Room room) {
        Message message = messageRepository.save(request.toEntity(user, room));
        server.getRoomOperations(request.getRoomId())
                .sendEvent(SocketEventProperty.MESSAGE_KEY, MessageResponseDto.of(message, room.getId()));
    }

    @Transactional(readOnly = true)
    public MessageListResponseDto getLastMessage(Long roomId, Pageable pageable) {
        return new MessageListResponseDto(
                messageRepository.findAllByRoom(roomFacade.findRoomById(roomId), pageable)
                        .stream().map(m -> MessageResponseDto.of(m, roomId)).collect(Collectors.toList()));
    }
}
