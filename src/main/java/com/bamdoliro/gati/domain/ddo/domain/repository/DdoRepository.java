package com.bamdoliro.gati.domain.ddo.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.type.ddo.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DdoRepository extends JpaRepository<Ddo, Long> {

    Optional<List<Ddo>> findByCommunityAndStatus(Community community, Status status);
}
