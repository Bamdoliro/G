package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.Member;
import com.bamdoliro.gati.domain.community.domain.repository.MemberRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.exception.UserNotCommunityMemberException;
import com.bamdoliro.gati.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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

    @DisplayName("[Facade] check member - community member가 아닌 경우")
    @Test
    void givenInvalidUserAndCommunity_whenCheckingMember_thenThrowsUserNotCommunityMemberException() {
        // given
        given(memberRepository.existsByUserAndCommunity(any(), any())).willReturn(false);

        // when and then
        assertThrows(UserNotCommunityMemberException.class, () ->
                memberFacade.checkMember(defaultUser, defaultCommunity));
        verify(memberRepository, times(1)).existsByUserAndCommunity(defaultUser, defaultCommunity);
    }

}