package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardFacade {

    private final BoardRepository boardRepository;

    public Board findBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        if (board.getStatus() == Status.DELETED)
            throw BoardNotFoundException.EXCEPTION;
        return board;
    }

    public Board findMyPosts(User currentUser) {
        return boardRepository.findByWriter(currentUser);
    }

    public List<Board> findCommunityPosts(Community community) {
        return boardRepository.findAllByCommunity(community);
    }

    public List<Board> findByCommunityAndStatus(Community community, Status existed) {
        return boardRepository.findByCommunityAndStatus(community, Status.EXISTED)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }
}
