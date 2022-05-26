package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.community.domain.Community;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class EditBoardRequest {

    private Long id;

    private String title;

    private String content;

}
