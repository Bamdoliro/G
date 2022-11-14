package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DdoResponseDto {

    private Long id;
    
    private String title;
    
    private String writerName;
    
    private int capacity;
    
    private int numberOfJoiner;
    
    private int numberOfRecommendation;

    public static DdoResponseDto of(Ddo ddo) {
        return DdoResponseDto.builder()
                .id(ddo.getId())
                .title(ddo.getTitle())
                .writerName(ddo.getWriter().getName())
                .capacity(ddo.getCapacity())
                .numberOfRecommendation(ddo.getRecommendList().size())
                .numberOfJoiner(ddo.getDdoJoinList().size())
                .build();
    }

}
