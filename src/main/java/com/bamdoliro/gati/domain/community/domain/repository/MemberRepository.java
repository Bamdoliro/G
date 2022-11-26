package com.bamdoliro.gati.domain.community.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserAndCommunity(User user, Community community);

    Optional<Member> findByUserAndCommunity(User user, Community community);

    @Query("SELECT m FROM Member m JOIN FETCH m.community WHERE m.user = :user")
    List<Member> findAllByUser(User user);
}
