package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.BoardLike;
import com.bamdoliro.gati.domain.board.domain.repository.LikeRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.facade.LikeFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final LikeFacade likeFacade;
    private final LikeRepository likeRepository;

    // 좋아요 Logic
    @Transactional
    public void like(Long boardId) {
        Board board = boardFacade.findBoardById(boardId);
        User user = userFacade.getCurrentUser();

        likeFacade.validateLike(board, user);

        BoardLike like = BoardLike.createBoardLike(board, user);

        likeRepository.save(like);
    }

    // 좋아요 취소
    @Transactional
    public void cancelLike(Long boardId) {
        Board board = boardFacade.findBoardById(boardId);
        User user = userFacade.getCurrentUser();

        likeRepository.deleteByBoardAndLiker(board, user);
    }
}
