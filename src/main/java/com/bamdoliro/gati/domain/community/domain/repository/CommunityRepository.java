package com.bamdoliro.gati.domain.community.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    Optional<Community> findByIdAndStatus(Long id, Status status);
    List<Community> findByNameContainingAndStatus(String name, Status status);
    Optional<Community> findByCodeAndStatus(String code, Status status);
    boolean existsByCodeAndStatus(String code, Status status);
}
