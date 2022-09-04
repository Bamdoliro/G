package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class UserResponseDto {

    private String name;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .build();
    }
}
