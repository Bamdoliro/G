package com.bamdoliro.gati.domain.user.presentation.dto.request;

import com.bamdoliro.gati.domain.user.domain.type.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateUserRequestDto {

    @NotNull(message = "이메일을 입력해 주세요.")
    @Email
    private final String email;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, max = 20)
    private final String password;

    @NotNull(message = "이름을 입력해 주세요.")
    @Size(max = 20)
    private final String name;

    @NotNull(message = "생일을 입력해 주세요.")
    private final int birthYear;

    @NotNull(message = "생일을 입력해 주세요.")
    private final int birthMonth;

    @NotNull(message = "생일을 입력해 주세요.")
    private final int birthDay;

    @NotNull(message = "성별을 선택해 주세요.")
    private final Gender gender;
}
