package com.bamdoliro.gati.domain.community.domain.repository;

import com.bamdoliro.gati.domain.community.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
