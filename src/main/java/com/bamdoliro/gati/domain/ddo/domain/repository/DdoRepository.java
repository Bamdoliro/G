package com.bamdoliro.gati.domain.ddo.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DdoRepository extends JpaRepository<Ddo, Long> {

    Optional<List<Ddo>> findByCommunityAndStatus(Community community, DdoStatus status);

    @Query("select d " +
            "from Ddo d " +
            "join d.community c " +
            "where c.id = :communityId and d.status = :status " +
            "order by d.recommendList.size DESC")
    Optional<List<Ddo>> findAllOrderByRecommendation(
            @Param("communityId") Long communityId, @Param("status") DdoStatus status
    );
}