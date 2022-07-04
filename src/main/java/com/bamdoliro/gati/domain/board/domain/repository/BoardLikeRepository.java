package com.bamdoliro.gati.domain.board.domain.repository;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.BoardLike;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    boolean existsByBoardAndLiker(Board board, User user);
    void deleteByBoardAndLiker(Board board, User liker);
}
