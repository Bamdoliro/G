package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardFacadeTest {

    @Mock private BoardRepository boardRepository;

    @InjectMocks private BoardFacade boardFacade;

    Board board = Board.builder()
            .title("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고")
            .content("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래")
            .build();

    @DisplayName("[facade] findBoardById - 해당 게시물이 존재할 때")
    @Test
    void givenBoardId_whenFindBoard_thenReturnBoard() {
        // Given
        given(boardRepository.findById(anyLong())).willReturn(Optional.ofNullable(board));

        // When
        Board findBoard = boardFacade.findBoardById(anyLong());

        // Then
        assertEquals("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고", findBoard.getTitle());
        assertEquals("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래", findBoard.getContent());
    }

    @DisplayName("[facade] findBoardById - 해당 게시물이 삭제됐거나 존재하지 않을 때")
    @Test
    void givenBoardId_whenFindBoard_thenThrowsBoardNotFoundException() {
        // Given
        given(boardRepository.findById(anyLong())).willReturn(Optional.empty());

        // When and Then
        assertThrows(BoardNotFoundException.class,
                () -> boardFacade.findBoardById(anyLong()));
    }

    @DisplayName("[facade] findByCommunityAndStatus")
    @Test
    void givenCommunityAndStatus_whenFindBoards_thenReturnBoardList() {
        // Given
        given(boardRepository.findByCommunityAndBoardStatus(any(), any()))
                .willReturn(Optional.of(List.of(board)));

        // When
        List<Board> findBoardList = boardFacade.findBoardsByCommunityAndStatus(any(), any());

        // Then
        assertEquals("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고", findBoardList.get(0).getTitle());
        assertEquals("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래", findBoardList.get(0).getContent());
    }

    @DisplayName("[facade] findByCommunityAndStatus - 커뮤니티에 게시물이 존재하지 않거나 전부 삭제되었을 때")
    @Test
    void givenCommunityAndStatus_whenFindBoards_thenThrowsBoardNotFoundException() {
        // Given
        given(boardRepository.findByCommunityAndBoardStatus(any(), any()))
                .willReturn(Optional.empty());

        // When and Then
        assertThrows(BoardNotFoundException.class,
                () -> boardFacade.findBoardsByCommunityAndStatus(any(), any()));
    }
}