package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.exception.AuthorityMismatchException;
import com.bamdoliro.gati.domain.community.exception.MemberNotFoundException;
import com.bamdoliro.gati.domain.community.exception.UserNotCommunityMemberException;
import com.bamdoliro.gati.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("[Facade] Community Member")
class MemberFacadeTest {

    @InjectMocks
    MemberFacade memberFacade;

    @Mock
    MemberRepository memberRepository;

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

    @DisplayName("[Facade] findMemberById")
    @Test
    void givenMemberId_whenFindingMember_thenReturnsMember() {
        // given
        given(memberRepository.findById(any())).willReturn(Optional.of(defaultMember));

        // when
        Member member = memberFacade.findMemberById(defaultMember.getId());

        // then
        verify(memberRepository, times(1)).findById(any());
        assertEquals(defaultMember.getCommunity(), member.getCommunity());
        assertEquals(defaultMember.getUser(), member.getUser());
        assertEquals(defaultMember.getAuthority(), member.getAuthority());
    }

    @DisplayName("[Facade] findMemberById - member 가 없는 경우")
    @Test
    void givenInvalidMemberId_whenFindingMember_thenThrowsMemberNotFoundException() {
        // given
        given(memberRepository.findById(any())).willReturn(Optional.empty());

        // when and then
        assertThrows(MemberNotFoundException.class, () ->
                memberFacade.findMemberById(defaultMember.getId()));
        verify(memberRepository, times(1)).findById(any());
    }

    @DisplayName("[Facade] findMemberByUserAndCommunity")
    @Test
    void givenUserAndCommunity_whenFindingMember_thenReturnsMember() {
        // given
        given(memberRepository.findByUserAndCommunity(any(), any())).willReturn(Optional.of(defaultMember));

        // when
        Member member = memberFacade.findMemberByUserAndCommunity(defaultUser, defaultCommunity);

        // then
        verify(memberRepository, times(1)).findByUserAndCommunity(any(), any());
        assertEquals(defaultMember.getUser(), member.getUser());
        assertEquals(defaultMember.getCommunity(), member.getCommunity());
        assertEquals(defaultMember.getAuthority(), member.getAuthority());
    }

    @DisplayName("[Facade] findMemberByUserAndCommunity - community 에 가입한 user 가 아닌 경우")
    @Test
    void givenInvalidUserAndCommunity_whenFindingMember_thenThrowsUserNotCommunityMemberException() {
        // given
        given(memberRepository.findByUserAndCommunity(any(), any())).willReturn(Optional.empty());

        // when and then
        assertThrows(UserNotCommunityMemberException.class, () ->
                memberFacade.findMemberByUserAndCommunity(defaultUser, defaultCommunity));
        verify(memberRepository, times(1)).findByUserAndCommunity(any(), any());
    }

    @DisplayName("[Facade] check member")
    @Test
    void givenUserAndCommunity_whenCheckingMember_thenChecksSuccessfully() {
        // given
        given(memberRepository.existsByUserAndCommunity(any(), any())).willReturn(true);

        // when
        memberFacade.checkMember(defaultUser, defaultCommunity);

        // then
        verify(memberRepository, times(1)).existsByUserAndCommunity(defaultUser, defaultCommunity);
    }

    @DisplayName("[Facade] check member - community member 가 아닌 경우")
    @Test
    void givenInvalidUserAndCommunity_whenCheckingMember_thenThrowsUserNotCommunityMemberException() {
        // given
        given(memberRepository.existsByUserAndCommunity(any(), any())).willReturn(false);

        // when and then
        assertThrows(UserNotCommunityMemberException.class, () ->
                memberFacade.checkMember(defaultUser, defaultCommunity));
        verify(memberRepository, times(1)).existsByUserAndCommunity(defaultUser, defaultCommunity);
    }

    @DisplayName("[Facade] check member authority")
    @Test
    void givenAuthority_whenCheckingMemberAuthority_thenThrowsAuthorityMismatchException() {
        // given

        // when
        memberFacade.checkMemberAuthority(defaultMember, Authority.MEMBER);

        // then
    }

    @DisplayName("[Facade] check member authority - authority가 다른 경우")
    @Test
    void givenInvalidAuthority_whenCheckingMemberAuthority_thenThrowsAuthorityMismatchException() {
        // given

        // when and then
        assertThrows(AuthorityMismatchException.class, () ->
                memberFacade.checkMemberAuthority(defaultMember, Authority.LEADER));
    }

}