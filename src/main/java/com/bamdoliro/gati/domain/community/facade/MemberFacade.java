package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.exception.UserNotCommunityMemberException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberRepository memberRepository;

    public void checkMember(User user, Community community) {
        if (!memberRepository.existsByUserAndCommunity(user, community)) {
            throw UserNotCommunityMemberException.EXCEPTION;
        }
    }
}
