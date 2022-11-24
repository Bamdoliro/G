package com.bamdoliro.gati.domain.ddo.presentation.dto.request;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDdoRequestDto {

    @NotNull
    private Long communityId;

    @NotNull
    @Length(min = 5, max = 15)
    private String title;

    @NotNull
    @Length(min = 10, max = 2500)
    private String content;

    @NotNull
    @Min(1)
    @Max(99)
    private int capacity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    public Ddo toEntity() {
        return Ddo.builder()
                .title(title)
                .content(content)
                .capacity(capacity)
                .startDate(startDate)
                .endDate(endDate)
                .status(DdoStatus.OPEN)
                .build();
    }
}
