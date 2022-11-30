package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.user.presentation.dto.response.UserProfileResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailDto {


    private String title;

    private String content;

    private int numberOfLikes;

    private UserProfileResponse userProfile;

    private LocalDateTime createdAt;

    public static BoardDetailDto of(Board board) {
        return BoardDetailDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .numberOfLikes(board.getLikes().size())
                .userProfile(UserProfileResponse.of(board.getWriter()))
                .createdAt(board.getCreatedAt())
                .build();
    }
}
