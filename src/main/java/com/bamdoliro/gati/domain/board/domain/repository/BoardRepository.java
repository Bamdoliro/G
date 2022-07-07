package com.bamdoliro.gati.domain.board.domain.repository;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.type.board.Status;
import com.bamdoliro.gati.domain.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<List<Board>> findByCommunityAndStatus(Community community, Status existed);
}
