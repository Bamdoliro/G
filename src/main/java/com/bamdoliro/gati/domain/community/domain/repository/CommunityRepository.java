package com.bamdoliro.gati.domain.community.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.type.CommunityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    Optional<Community> findByIdAndCommunityStatus(Long id, CommunityStatus communityStatus);
    List<Community> findByNameContainingAndCommunityStatus(String name, CommunityStatus communityStatus);
    Optional<Community> findByCodeAndCommunityStatus(String code, CommunityStatus communityStatus);
    boolean existsByCodeAndCommunityStatus(String code, CommunityStatus communityStatus);
}
