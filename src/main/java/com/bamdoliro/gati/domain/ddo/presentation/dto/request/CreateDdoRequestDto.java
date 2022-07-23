package com.bamdoliro.gati.domain.ddo.presentation.dto.request;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.ddo.Status;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
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
    private int maxNumber;

    public Ddo toEntity() {
        return Ddo.builder()
                .title(title)
                .content(content)
                .maxNumber(maxNumber)
                .status(Status.OPEN)
                .build();
    }
}
