package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.Report;
import com.bamdoliro.gati.domain.board.domain.repository.ReportRepository;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportCategory;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportStatus;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportType;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.facade.ReportFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateReportRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.ReportDetailResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock private ReportFacade reportFacade;
    @Mock private UserFacade userFacade;
    @Mock private ReportRepository reportRepository;
    @Mock private BoardFacade boardFacade;

    @InjectMocks private ReportService reportService;

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
            .reportType(ReportType.BOARD)
            .reportCategory(ReportCategory.NUDITY_PHOTO)
            .title("제목 is 제목")
            .content("content는 content")
            .board(board)
            .user(user)
            .build();

    @DisplayName("[service] reportPost")
    @Test
    void givenBoardIdAndRequestDto_whenReportBoard_thenCreateReport() {
        // Given
        CreateReportRequestDto request = CreateReportRequestDto.builder()
                .reportType(ReportType.BOARD)
                .reportCategory(ReportCategory.NUDITY_PHOTO)
                .title("제목 is 제목")
                .content("content는 content")
                .build();

        given(boardFacade.findBoardById(anyLong())).willReturn(board);
        given(userFacade.getCurrentUser()).willReturn(user);

        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);

        // When
        reportService.reportPost(anyLong(), request);

        // Then
        verify(reportRepository, times(1))
                .save(captor.capture());

        Report savedReport = captor.getValue();

        assertEquals(ReportStatus.UNSOLVED, savedReport.getReportStatus());
        assertEquals(ReportType.BOARD, savedReport.getReportType());
        assertEquals(ReportCategory.NUDITY_PHOTO, savedReport.getReportCategory());
        assertEquals("제목 is 제목", savedReport.getTitle());
        assertEquals("content는 content", savedReport.getContent());
    }

    @DisplayName("[service] getBoardReportList")
    @Test
    void givenBoardId_whenFindReportList_thenReturnReportList() {
        // Given
        given(reportFacade.findReportById(anyLong())).willReturn(report);

        // When
        ReportDetailResponseDto response = reportService.getReportDetail(anyLong());

        // Then
        assertEquals(ReportStatus.UNSOLVED, response.getReportStatus());
        assertEquals(ReportType.BOARD, response.getReportType());
        assertEquals(ReportCategory.NUDITY_PHOTO, response.getReportCategory());
        assertEquals("제목 is 제목", response.getTitle());
        assertEquals("content는 content", response.getContent());
    }

}