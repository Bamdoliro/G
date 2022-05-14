package com.bamdoliro.gati.domain.user.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class getUserResponseDto {

    private String email;
    private String name;
    private LocalDate birth;
    private Gender gender;
    private Authority authority;
    private Status status;

    public static getUserResponseDto fromUser(User user) {
        return getUserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .birth(user.getBirth())
                .gender(user.getGender())
                .authority(user.getAuthority())
                .status(user.getStatus())
                .build();
    }
}
