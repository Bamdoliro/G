package com.bamdoliro.gati.domain.chat.presentation.dto.response;

import com.bamdoliro.gati.domain.chat.domain.Room;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseDto {

    private Long id;
    private int numberOfMembers;
    private String name;

    public static RoomResponseDto of(Room room, int numberOfMembers) {
        return RoomResponseDto.builder()
                .id(room.getId())
                .name(room.getName())
                .numberOfMembers(numberOfMembers)
                .build();
    }
}
