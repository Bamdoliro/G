package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.user.presentation.dto.response.UserProfileResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class DdoDetailResponseDto {

    private String title;

    private String content;

    private DdoStatus status;

    private int capacity;

    private int numberOfJoiner;

    private String writerName;

    private int numberOfRecommendation;

    private LocalDate startDate;

    private LocalDate endDate;

    private UserProfileResponse userProfile;

    private LocalDateTime createdAt;

    public static DdoDetailResponseDto of(Ddo ddo) {
        return DdoDetailResponseDto.builder()
                .title(ddo.getTitle())
                .content(ddo.getContent())
                .status(ddo.getStatus())
                .capacity(ddo.getCapacity())
                .numberOfJoiner(ddo.getDdoJoinList().size())
                .numberOfRecommendation(ddo.getRecommendList().size())
                .writerName(ddo.getWriter().getName())
                .startDate(ddo.getStartDate())
                .endDate(ddo.getEndDate())
                .userProfile(UserProfileResponse.of(ddo.getWriter()))
                .createdAt(ddo.getCreatedAt())
                .build();
    }
}
