package com.bamdoliro.gati.domain.ddo.domain.repository;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DdoRepository extends JpaRepository<Ddo, Long> {

    @EntityGraph(attributePaths = {"recommendList", "ddoJoinList", "writer" })
    @Query("SELECT d " +
            "FROM Ddo d " +
            "JOIN d.community c " +
            "WHERE c.id = :communityId and d.status = :status " +
            "ORDER BY d.recommendList.size DESC")
    Optional<List<Ddo>> findAllOrderByRecommendation(
            @Param("communityId") Long communityId, @Param("status") DdoStatus status
    );

    @Override
    @EntityGraph(attributePaths = {"recommendList", "ddoJoinList"})
    Optional<Ddo> findById(Long id);
}