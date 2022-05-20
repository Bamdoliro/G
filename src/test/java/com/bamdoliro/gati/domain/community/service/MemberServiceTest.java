package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.user.domain.User;
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
    private UserService userService;

    private final Community defaultCommunity = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
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

    @DisplayName("[Service] Community Member 가입")
    @Test
    void givenUser_whenJoinCommunity_thenCreatesCommunityMember() {
        // given
        given(memberRepository.save(any())).willReturn(defaultMember);
        given(communityFacade.findCommunityById(any())).willReturn(defaultCommunity);
        given(userService.getCurrentUser()).willReturn(defaultUser);
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        // when
        memberService.joinCommunity(defaultCommunity.getId());

        // then
        verify(memberRepository, times(1)).save(captor.capture());
        Member savedMember = captor.getValue();
        assertEquals(defaultUser, savedMember.getUser());
        assertEquals(defaultCommunity, savedMember.getCommunity());
        assertEquals(Authority.MEMBER, savedMember.getAuthority());
    }
}