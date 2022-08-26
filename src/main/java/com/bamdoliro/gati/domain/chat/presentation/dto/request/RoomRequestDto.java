package com.bamdoliro.gati.domain.chat.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    @NotNull
    private String roomId;
}
