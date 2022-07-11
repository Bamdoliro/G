package com.bamdoliro.gati.domain.ddo.presentation.dto.request;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.ddo.Status;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
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
    @Size(min = 1, max = 99)
    private int numberOfPeople;

    public Ddo toEntity() {
        return Ddo.builder()
                .title(title)
                .content(content)
                .maxNumber(numberOfPeople)
                .status(Status.OPEN)
                .build();
    }
}
