package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class ReportResponseDto {

    @NotNull
    private String title;

    @NotNull
    private String boardTitle;

    @NotNull
    private String userName;

    public static ReportResponseDto of(Report report){
        return ReportResponseDto.builder()
                .title(report.getTitle())
                .boardTitle(report.getBoard().getTitle())
                .userName(report.getUser().getName())
                .build();
    }
}
