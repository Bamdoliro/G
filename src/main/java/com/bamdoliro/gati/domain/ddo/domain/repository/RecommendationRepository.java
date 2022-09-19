package com.bamdoliro.gati.domain.ddo.domain.repository;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.Recommendation;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    void deleteByUserAndDdo(User user, Ddo ddo);

    boolean existsByUserAndDdo(User user, Ddo ddo);
}
