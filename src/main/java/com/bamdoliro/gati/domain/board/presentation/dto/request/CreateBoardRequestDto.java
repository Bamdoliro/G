package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateBoardRequestDto {

    private final CommunityFacade communityFacade;
    private final UserFacade userFacade;

    @NotNull
    private Long communityId;
    @NotNull
    private String title;
    @NotNull
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .community(
                        communityFacade.findCommunityById(communityId)
                )
                .writer(userFacade.getCurrentUser())
                .build();
    }
}
