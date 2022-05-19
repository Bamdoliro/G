package com.bamdoliro.gati.domain.board.domain.repository;

import com.bamdoliro.gati.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BoardRepository extends JpaRepository<Board, Long> {

    ArrayList<Board> findAllByWriter(String writer);


    ArrayList<Board> findAllByBoardType(BoardType type);
}
