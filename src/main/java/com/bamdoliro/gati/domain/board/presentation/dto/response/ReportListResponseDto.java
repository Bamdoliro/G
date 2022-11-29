package com.bamdoliro.gati.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReportListResponseDto {

    private List<ReportResponseDto> reportList;
}
