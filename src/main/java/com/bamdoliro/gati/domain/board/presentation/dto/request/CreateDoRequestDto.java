package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.DoPost;
import com.bamdoliro.gati.domain.board.domain.type.doPost.Status;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateDoRequestDto {

    @NotNull
    @Length(min = 5, max = 15)
    private String title;
    @NotNull
    @Length(min = 10, max = 2500)
    private String content;
    @NotNull
    @Size(min = 1, max = 99)
    private int numberOfPeople;

    public DoPost toEntity() {
        return DoPost.builder()
                .title(title)
                .content(content)
                .numberOfPeople(numberOfPeople)
                .status(Status.OPEN)
                .build();
    }
}
