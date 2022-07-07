package com.bamdoliro.gati.domain.board.domain.repository;

import com.bamdoliro.gati.domain.board.domain.DoPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoRepository extends JpaRepository<DoPost, Long> {
}
