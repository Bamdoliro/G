package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateReportRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportDetailResponseDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportResponseDto;
import com.bamdoliro.gati.domain.board.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 게시물 신고하기
    @PostMapping("/{boardId}")
    public void reportPost(
            @PathVariable Long boardId,
            @RequestBody CreateReportRequestDto request
    ) {
        reportService.reportPost(boardId, request);
    }

    // 특정 게시물의 신고 리스트 조회
    @GetMapping("/{boardId}")
    public List<ReportResponseDto> getReportList(@PathVariable Long boardId) {
        return reportService.getBoardReportList(boardId);
    }

    // 신고 디테일 조회
    @GetMapping("/{reportId}/detail")
    public ReportDetailResponseDto getReportDetail(@PathVariable Long reportId) {
        return reportService.getReportDetail(reportId);
    }
}
