package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.board.BoardStatus;
import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import com.bamdoliro.gati.domain.community.domain.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardFacade {

    private final BoardRepository boardRepository;

    // 삭제되지 않는 게시물 id 로 찾기
    public Board findBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        if (board.getBoardStatus() == BoardStatus.DELETED)
            throw BoardNotFoundException.EXCEPTION;
        return board;
    }

    // 공동체랑 삭제 여부로 찾기
    public List<Board> findBoardsByCommunityAndStatus(Community community, BoardStatus boardStatus) {
        return boardRepository.findByCommunityAndBoardStatus(community, boardStatus)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }

}
