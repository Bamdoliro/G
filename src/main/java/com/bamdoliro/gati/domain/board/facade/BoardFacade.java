package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardFacade {

    private final BoardRepository boardRepository;

    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }



}
