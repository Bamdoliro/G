package com.bamdoliro.gati.domain.community.presentation.dto.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UpdateCommunityRequestDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Size(max = 1000)
    private String introduction;

    @NotNull
    private Boolean isPublic;

    @Nullable
    @Size(min = 4, max = 4)
    private String password;

}
