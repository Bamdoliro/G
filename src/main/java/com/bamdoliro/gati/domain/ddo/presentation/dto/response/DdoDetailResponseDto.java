package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.ddo.facade.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.facade.ddo.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class DdoDetailResponseDto {

    private String title;

    private String content;

    private Status status;

    private int maxNumber;

    private int numberOfJoiner;

    private String writerName;

    private int numberOfRecommendation;

    public static DdoDetailResponseDto of(Ddo ddo) {
        return DdoDetailResponseDto.builder()
                .title(ddo.getTitle())
                .content(ddo.getContent())
                .status(ddo.getStatus())
                .maxNumber(ddo.getMaxNumber())
                .numberOfJoiner(ddo.getDdoJoinList().size())
                .numberOfRecommendation(ddo.getRecommendList().size())
                .writerName(ddo.getWriter().getName())
                .build();
    }
}
