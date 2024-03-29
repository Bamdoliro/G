package com.bamdoliro.gati.domain.ddo.presentation.dto.request;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private List<String> images = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;

    public Ddo toEntity() {
        return Ddo.builder()
                .title(title)
                .content(content)
                .capacity(capacity)
                .images(images)
                .startDate(startDate)
                .endDate(endDate)
                .status(DdoStatus.OPEN)
                .build();
    }
}
