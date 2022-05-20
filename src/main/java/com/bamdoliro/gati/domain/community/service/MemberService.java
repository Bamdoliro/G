package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CommunityFacade communityFacade;
    private final UserFacade userFacade;

    public void joinCommunity(Long communityId) {
        Community community = communityFacade.findCommunityById(communityId);
        User user = userFacade.getCurrentUser();

        memberRepository.save(Member.builder()
                .user(user)
                .community(community)
                .authority(Authority.MEMBER)
                .build()
        );
    }

}
