package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.repository.ReportRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.facade.ReportFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateReportRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportDetailResponseDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportListResponseDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportFacade reportFacade;
    private final UserFacade userFacade;
    private final ReportRepository reportRepository;
    private final BoardFacade boardFacade;

    @Transactional
    public void reportPost(Long boardId, CreateReportRequestDto request) {
        Board board = boardFacade.findBoardById(boardId);
        User user = userFacade.getCurrentUser();

        reportFacade.validateReport(board, user);

        Report report = request.toEntity(board, user);
        report.setRelation();

        reportRepository.save(report);
    }

    @Transactional(readOnly = true)
    public ReportListResponseDto getBoardReportList(Long boardId) {
        return new ReportListResponseDto(
                boardFacade.findBoardById(boardId).getReportList().stream()
                        .map(ReportResponseDto::of)
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public ReportDetailResponseDto getReportDetail(Long reportId) {
        return ReportDetailResponseDto.of(reportFacade.findReportById(reportId));
    }
}
