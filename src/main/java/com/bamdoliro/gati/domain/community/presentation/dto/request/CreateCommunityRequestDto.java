package com.bamdoliro.gati.domain.community.presentation.dto.request;

import com.bamdoliro.gati.domain.community.domain.Community;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateCommunityRequestDto {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 1000)
    private String introduction;

    @NotNull
    private int capacity;

    @NotNull
    private Boolean isPublic;

    @Nullable
    private String password;

    public Community toEntity(String code) {
        return Community.builder()
                .name(name)
                .introduction(introduction)
                .capacity(capacity)
                .code(code)
                .isPublic(isPublic)
                .password(password)
                .build();
    }
}