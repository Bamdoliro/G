package com.bamdoliro.gati.domain.board.presentation.dto.response;

import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportCategory;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportStatus;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ReportDetailResponseDto {

    @NotNull
    private String boardTitle;

    @NotNull
    private String userName;

    @NotNull
    private ReportCategory reportCategory;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private ReportStatus reportStatus;

    public static ReportDetailResponseDto of (Report report) {
        return ReportDetailResponseDto.builder()
                .boardTitle(report.getBoard().getTitle())
                .userName(report.getUser().getName())
                .reportCategory(report.getReportCategory())
                .title(report.getTitle())
                .content(report.getContent())
                .reportStatus(report.getReportStatus())
                .build();
    }

}
