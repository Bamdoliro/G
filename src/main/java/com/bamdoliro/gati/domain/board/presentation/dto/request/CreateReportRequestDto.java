package com.bamdoliro.gati.domain.board.presentation.dto.request;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportCategory;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportType;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@Builder
public class CreateReportRequestDto {

    @NotNull
    private ReportType reportType;
    @NotNull
    private ReportCategory reportCategory;
    @NotNull
    @Size(min = 3, max = 20)
    private String title;
    @NotNull
    @Size(min = 10, max = 250)
    private String content;

    public Report toEntity(Board board, User user) {
        return Report.builder()
                .board(board)
                .user(user)
                .type(getReportType())
                .category(getReportCategory())
                .title(getTitle())
                .content(getContent())
                .build();
    }

}
