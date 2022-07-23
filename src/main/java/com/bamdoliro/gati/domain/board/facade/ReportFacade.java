package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.repository.ReportRepository;
import com.bamdoliro.gati.domain.board.exception.ReportNotFoundException;
import com.bamdoliro.gati.domain.board.exception.ReportOverlapException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportRepository reportRepository;

    // 신고 중복 체크
    public void validateReport(Board board, User user) {
        if (reportRepository.existsByBoardAndUser(board, user)) {
            throw ReportOverlapException.EXCEPTION;
        }
    }

    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> ReportNotFoundException.EXCEPTION);
    }
}
