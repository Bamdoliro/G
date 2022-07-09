package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.DoPost;
import com.bamdoliro.gati.domain.board.domain.type.doPost.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class DoDetailResponseDto {

    private String title;

    private String content;

    private Status status;

    private int maxNumber;

    private int numberOfJoiner;

    private String writerName;

    private int numberOfRecommendation;

    public static DoDetailResponseDto of(DoPost doPost) {
        return DoDetailResponseDto.builder()
                .title(doPost.getTitle())
                .content(doPost.getContent())
                .status(doPost.getStatus())
                .maxNumber(doPost.getMaxNumber())
                .numberOfJoiner(doPost.getJoinList().size())
                .numberOfRecommendation(doPost.getRecommendList().size())
                .writerName(doPost.getWriter().getName())
                .build();
    }
}
