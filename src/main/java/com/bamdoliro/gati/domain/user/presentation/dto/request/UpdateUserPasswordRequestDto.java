package com.bamdoliro.gati.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordRequestDto {

    @NotNull(message = "변경할 비밀번호를 입력해 주세요.")
    private String password;

    @NotNull(message = "현재 비밀번호를 입력해 주세요.")
    private String currentPassword;
}
