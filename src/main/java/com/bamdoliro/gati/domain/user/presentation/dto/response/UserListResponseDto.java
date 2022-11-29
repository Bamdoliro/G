package com.bamdoliro.gati.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListResponseDto {

    private List<UserResponseDto> userList;
}
