package com.bamdoliro.gati.domain.user.presentation.dto.response;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private Long id;
    private String name;
    private String image;

    public static UserProfileResponse of(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .image(user.getProfileImg())
                .build();
    }
}
