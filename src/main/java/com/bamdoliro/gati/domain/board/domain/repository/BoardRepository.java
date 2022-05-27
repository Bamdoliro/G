package com.bamdoliro.gati.domain.board.domain.repository;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByWriter(User currentUser);
}
