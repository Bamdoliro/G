package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class DdoResponseDto {

    private Long id;

    private String title;

    private String writerName;

    private int numberOfRecommendation;

    private int maxNumber;

    private int numberOfJoiner;

    public static DdoResponseDto of(Ddo ddo) {
        return DdoResponseDto.builder()
                .id(ddo.getId())
                .title(ddo.getTitle())
                .writerName(ddo.getWriter().getName())
                .numberOfRecommendation(ddo.getRecommendList().size())
                .numberOfJoiner(ddo.getDdoJoinList().size())
                .numberOfRecommendation(ddo.getRecommendList().size())
                .build();
    }

}
