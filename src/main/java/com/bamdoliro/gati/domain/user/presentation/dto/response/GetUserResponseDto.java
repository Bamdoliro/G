package com.bamdoliro.gati.domain.user.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GetUserResponseDto {

    private String email;
    private String name;
    private LocalDate birth;
    private Gender gender;
    private Authority authority;
    private UserStatus status;

    public static GetUserResponseDto of(User user) {
        return GetUserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .birth(user.getBirth())
                .gender(user.getGender())
                .authority(user.getAuthority())
                .status(user.getStatus())
                .build();
    }
}
