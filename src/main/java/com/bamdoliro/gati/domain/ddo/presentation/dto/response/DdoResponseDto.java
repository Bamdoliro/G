package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class DdoResponseDto {

    private Long id;
    
    private String title;
    
    private String content;
    
    private int capacity;
    
    private LocalDate startDate;

    private LocalDate endDate;

    public static DdoResponseDto of(Ddo ddo) {
        return DdoResponseDto.builder()
                .id(ddo.getId())
                .title(ddo.getTitle())
                .content(ddo.getContent())
                .capacity(ddo.getCapacity())
                .startDate(ddo.getStartDate())
                .endDate(ddo.getEndDate())
                .build();
    }

}
