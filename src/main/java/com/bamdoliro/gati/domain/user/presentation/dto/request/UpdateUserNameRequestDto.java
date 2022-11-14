package com.bamdoliro.gati.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserNameRequestDto {

    @NotNull(message = "변경할 이름을 입력해 주세요.")
    private String name;
}
