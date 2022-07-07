package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.LikeRepository;
import com.bamdoliro.gati.domain.board.exception.LikeOverlapException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeFacade {

    private final LikeRepository boardLikeRepository;

    // 좋아요 충복 체크
    public void validateLike(Board board, User user) {
        if (boardLikeRepository.existsByBoardAndLiker(board, user)) {
            throw LikeOverlapException.EXCEPTION;
        }
    }

}
