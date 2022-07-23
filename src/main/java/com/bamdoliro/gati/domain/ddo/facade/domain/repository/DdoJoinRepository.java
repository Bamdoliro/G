package com.bamdoliro.gati.domain.ddo.facade.domain.repository;


import com.bamdoliro.gati.domain.ddo.facade.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.facade.domain.DdoJoin;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DdoJoinRepository extends JpaRepository<DdoJoin, Long> {

    boolean existsByDdoAndJoiner(Ddo ddo, User joiner);
}
