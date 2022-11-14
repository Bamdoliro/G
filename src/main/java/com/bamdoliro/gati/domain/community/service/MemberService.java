package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.exception.BadChangeCommunityLeaderRequestException;
import com.bamdoliro.gati.domain.community.exception.CommunityPasswordMismatchException;
import com.bamdoliro.gati.domain.community.exception.LeaderCannotLeaveCommunityException;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.community.presentation.dto.request.JoinCommunityRequestDto;
import com.bamdoliro.gati.domain.community.presentation.dto.request.ChangeCommunityLeaderRequestDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CommunityFacade communityFacade;
    private final UserFacade userFacade;
    private final MemberFacade memberFacade;

    @Transactional
    public void joinCommunity(JoinCommunityRequestDto dto, Authority authority) {
        Community community = communityFacade.findCommunityById(dto.getCommunityId());
        validateCommunityPassword(community, dto.getPassword());

        memberRepository.save(Member.builder()
                .user(userFacade.getCurrentUser())
                .community(community)
                .authority(authority)
                .build()
        );
    }

    private void validateCommunityPassword(Community community, String password) {
        if (!community.getIsPublic() && !community.getPassword().equals(password)) {
            throw CommunityPasswordMismatchException.EXCEPTION;
        }
    }

    @Transactional
    public void joinCommunity(Community community, Authority authority) {
        memberRepository.save(Member.builder()
                .user(userFacade.getCurrentUser())
                .community(community)
                .authority(authority)
                .build()
        );
    }

    @Transactional
    public void changeCommunityLeader(ChangeCommunityLeaderRequestDto dto) {
        User leaderUser = userFacade.getCurrentUser();
        Community community = communityFacade.findCommunityById(dto.getCommunityId());
        Member memberToBeMember = memberFacade.findMemberByUserAndCommunity(leaderUser, community);
        Member memberToBeLeader = memberFacade.findMemberById(dto.getMemberToBeLeaderId());
        validateChangeCommunityLeader(memberToBeMember, memberToBeLeader);

        memberToBeLeader.updateAuthority(Authority.LEADER);
        memberToBeMember.updateAuthority(Authority.MEMBER);
    }

    private void validateChangeCommunityLeader(Member leader, Member member) {
        if (
                !leader.getCommunity().equals(member.getCommunity()) ||
                leader.getAuthority() != Authority.LEADER ||
                member.getAuthority() != Authority.MEMBER
        ) {
            throw BadChangeCommunityLeaderRequestException.EXCEPTION;
        }
    }

    @Transactional
    public void leaveCommunity(Long communityId) {
        Member member = memberFacade.findMemberByUserAndCommunity(
                userFacade.getCurrentUser(),
                communityFacade.findCommunityById(communityId)
        );
        validateLeaveCommunityAuthority(member);

        memberRepository.delete(member);
    }

    private void validateLeaveCommunityAuthority(Member member) {
        if (member.getAuthority() == Authority.LEADER) {
            throw LeaderCannotLeaveCommunityException.EXCEPTION;
        }
    }
}
