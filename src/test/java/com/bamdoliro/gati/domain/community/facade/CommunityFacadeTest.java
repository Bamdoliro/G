package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommunityFacadeTest {

    @InjectMocks
    private CommunityFacade communityFacade;

    @Mock private CommunityRepository communityRepository;

    private final Community defaultCommunity = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

    @DisplayName("[Facade] checkCode")
    @Test
    void givenCode_whenCheckingCode_thenChecksAndReturnsTrue() {
        // given
        given(communityRepository.existsByCode(anyString())).willReturn(false);
        // when
        boolean result = communityFacade.checkCode("imC0DE");

        // then
        assertTrue(result);
    }

    @DisplayName("[Facade] checkCode - 이미 존재하는 코드일 경우")
    @Test
    void givenCode_whenCheckingCode_thenChecksAndReturnsFalse() {
        // given
        given(communityRepository.existsByCode(anyString())).willReturn(true);

        // when
        boolean result = communityFacade.checkCode("imC0DE");

        // then
        assertFalse(result);
    }
}