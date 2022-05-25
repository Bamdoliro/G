package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateBoardRequestDto {

    @NotNull
    private Long communityId;
    @NotNull
    private String title;
    @NotNull
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
