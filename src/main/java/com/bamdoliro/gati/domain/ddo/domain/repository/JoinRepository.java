package com.bamdoliro.gati.domain.ddo.domain.repository;


import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.Join;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<Join, Long> {

    boolean existsByDdoAndJoiner(Ddo ddo, User joiner);
}
