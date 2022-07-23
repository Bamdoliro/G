package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateBoardRequestDto {

    @NotNull
    private Long communityId;

    @NotNull
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @Size(min = 10, max = 4000)
    private String content;

    public Board toEntity(User writer, Community community) {
        return Board.builder()
                .title(title)
                .content(content)
                .community(community)
                .writer(writer)
                .build();
    }
}
