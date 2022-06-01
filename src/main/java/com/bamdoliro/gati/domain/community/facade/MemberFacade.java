package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.exception.MemberNotFoundException;
import com.bamdoliro.gati.domain.community.exception.UserNotCommunityMemberException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberRepository memberRepository;

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

    public Member findMemberByUserAndCommunity(User user, Community community) {
        return memberRepository.findByUserAndCommunity(user, community)
                .orElseThrow(() -> UserNotCommunityMemberException.EXCEPTION);
    }

    public void checkMember(User user, Community community) {
        if (!memberRepository.existsByUserAndCommunity(user, community)) {
            throw UserNotCommunityMemberException.EXCEPTION;
        }
    }
}
