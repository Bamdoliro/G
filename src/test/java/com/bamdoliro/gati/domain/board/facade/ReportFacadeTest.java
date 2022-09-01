package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.repository.ReportRepository;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportCategory;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportStatus;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportType;
import com.bamdoliro.gati.domain.board.exception.ReportNotFoundException;
import com.bamdoliro.gati.domain.board.exception.ReportOverlapException;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportFacadeTest {

    @Mock private ReportRepository reportRepository;

    @InjectMocks private ReportFacade reportFacade;

    User user = User.builder()
            .email("gati@bamdoliro.com")
            .name("김가티")
            .password("12345678910")
            .authority(Authority.ROLE_USER)
            .gender(Gender.FEMALE)
            .birth(LocalDate.of(2022,2,2))
            .status(UserStatus.NOT_VERIFIED)
            .build();

    Board board = Board.builder()
            .title("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고")
            .content("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래")
            .writer(user)
            .build();

    Report report = Report.builder()
            .reportCategory(ReportCategory.NUDITY_PHOTO)
            .title("제목 is 제목")
            .content("content는 content")
            .board(board)
            .user(user)
            .build();

    @DisplayName("[facade] validationReport - 처음 신고할 때")
    @Test
    void givenBoardAndUser_whenValidateReport_thenValidateSuccessfully() {
        // Given
        given(reportRepository.existsByBoardAndUser(any(), any())).willReturn(false);

        // When and Then
        reportFacade.validateReport(any(), any());

        verify(reportRepository, times(1))
                .existsByBoardAndUser(any(), any());
    }

    @DisplayName("[facade] validationReport - 같은 사람이 같은 게시물에 또 신고할 때")
    @Test
    void givenBoardAndUser_whenValidateReport_thenThrowsReportOverlapException() {
        // Given
        given(reportRepository.existsByBoardAndUser(any(), any())).willReturn(true);

        // When and Then
        assertThrows(ReportOverlapException.class,
                () -> reportFacade.validateReport(any(), any()));
    }

    @DisplayName("[facade] findReportById - 신고 내역이 있는 게시물의 신고 조회")
    @Test
    void givenBoardId_whenFindReport_thenThrowsReportNotFoundException() {
        // Given
        given(reportRepository.findById(anyLong())).willReturn(Optional.empty());

        // When and Then
        assertThrows(ReportNotFoundException.class,
                () -> reportFacade.findReportById(anyLong()));
    }
}