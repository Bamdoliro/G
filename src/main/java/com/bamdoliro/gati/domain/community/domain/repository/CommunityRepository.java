package com.bamdoliro.gati.domain.community.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findByNameContaining(String name);
    Optional<Community> findByCode(String code);
    boolean existsByCode(String code);
}
