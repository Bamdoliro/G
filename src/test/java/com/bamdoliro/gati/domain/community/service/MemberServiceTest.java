package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.presentation.dto.request.JoinCommunityRequestDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CommunityFacade communityFacade;

    @Mock
    private UserFacade userFacade;

    private final Community defaultCommunity = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

    private final Community defaultPrivateCommunity = Community.builder()
            .name("우리지브")
            .introduction("키키")
            .numberOfPeople(200)
            .isPublic(false)
            .password("1234")
            .build();

    private final User defaultUser = User.builder()
            .name("김가티")
            .email("gati@bamdoliro.com")
            .build();

    private final Member defaultMember = Member.builder()
            .community(defaultCommunity)
            .user(defaultUser)
            .authority(Authority.MEMBER)
            .build();

    private final Member defaultMemberInPrivate = Member.builder()
            .community(defaultPrivateCommunity)
            .user(defaultUser)
            .authority(Authority.MEMBER)
            .build();

    @DisplayName("[Service] Public Community Member 가입")
    @Test
    void givenJoinCommunityRequestDto_whenJoinPublicCommunity_thenCreatesCommunityMember() {
        // given
        given(memberRepository.save(any())).willReturn(defaultMember);
        given(communityFacade.findCommunityById(any())).willReturn(defaultCommunity);
        given(userFacade.getCurrentUser()).willReturn(defaultUser);
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        // when
        memberService.joinCommunity(new JoinCommunityRequestDto(
                defaultCommunity.getId(),
                null
        ), Authority.MEMBER);

        // then
        verify(memberRepository, times(1)).save(captor.capture());
        Member savedMember = captor.getValue();
        assertEquals(defaultUser, savedMember.getUser());
        assertEquals(defaultCommunity, savedMember.getCommunity());
        assertEquals(Authority.MEMBER, savedMember.getAuthority());
    }

    @DisplayName("[Service] Private Community Member 가입")
    @Test
    void givenJoinCommunityRequestDto_whenJoinPrivateCommunity_thenCreatesCommunityMember() {
        // given
        given(memberRepository.save(any())).willReturn(defaultMemberInPrivate);
        given(communityFacade.findCommunityById(any())).willReturn(defaultPrivateCommunity);
        given(userFacade.getCurrentUser()).willReturn(defaultUser);
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        // when
        memberService.joinCommunity(new JoinCommunityRequestDto(
                defaultPrivateCommunity.getId(),
                "1234"
        ), Authority.MEMBER);

        // then
        verify(memberRepository, times(1)).save(captor.capture());
        Member savedMember = captor.getValue();
        assertEquals(defaultUser, savedMember.getUser());
        assertEquals(defaultPrivateCommunity, savedMember.getCommunity());
        assertEquals(Authority.MEMBER, savedMember.getAuthority());
    }
}