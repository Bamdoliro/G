package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.exception.BadPasswordAndCommunityTypeException;
import com.bamdoliro.gati.domain.community.exception.CommunityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommunityFacadeTest {

    @InjectMocks
    private CommunityFacade communityFacade;

    @Mock
    private CommunityRepository communityRepository;

    private final Community defaultCommunity = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

    @DisplayName("[Facade] Community Id로 찾기")
    @Test
    void givenId_whenFindingCommunity_thenReturnsCommunity() {
        // given
        given(communityRepository.findById(any())).willReturn(Optional.of(defaultCommunity));

        // when
        Community foundCommunity = communityFacade.findCommunityById(defaultCommunity.getId());

        // then
        verify(communityRepository, times(1)).findById(any());
        assertEquals(defaultCommunity.getName(), foundCommunity.getName());
        assertEquals(defaultCommunity.getIntroduction(), foundCommunity.getIntroduction());
        assertEquals(defaultCommunity.getNumberOfPeople(), foundCommunity.getNumberOfPeople());
        assertEquals(defaultCommunity.getIsPublic(), foundCommunity.getIsPublic());
    }

    @DisplayName("[Facade] Community Id로 찾기 - 없는 경우")
    @Test
    void givenInvalidId_whenFindingCommunity_thenThrowsCommunityNotFoundException() {
        // given
        given(communityRepository.findById(any())).willReturn(Optional.empty());

        // when and then
        assertThrows(CommunityNotFoundException.class, () ->
                communityFacade.findCommunityById(defaultCommunity.getId()));
        verify(communityRepository, times(1)).findById(any());
    }

    @DisplayName("[Facade] Community Code 로 찾기")
    @Test
    void givenCode_whenFindingCommunity_thenReturnsCommunity() {
        // given
        given(communityRepository.findByCode(any())).willReturn(Optional.of(defaultCommunity));

        // when
        Community foundCommunity = communityFacade.findCommunityByCode(defaultCommunity.getCode());

        // then
        verify(communityRepository, times(1)).findByCode(any());
        assertEquals(defaultCommunity.getName(), foundCommunity.getName());
        assertEquals(defaultCommunity.getIntroduction(), foundCommunity.getIntroduction());
        assertEquals(defaultCommunity.getNumberOfPeople(), foundCommunity.getNumberOfPeople());
        assertEquals(defaultCommunity.getIsPublic(), foundCommunity.getIsPublic());
    }

    @DisplayName("[Facade] Community Code로 찾기 - 없는 경우")
    @Test
    void givenInvalidCode_whenFindingCommunity_thenThrowsCommunityNotFoundException() {
        // given
        given(communityRepository.findByCode(any())).willReturn(Optional.empty());

        // when and then
        assertThrows(CommunityNotFoundException.class, () ->
                communityFacade.findCommunityByCode(defaultCommunity.getCode()));
        verify(communityRepository, times(1)).findByCode(any());
    }

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

    @DisplayName("[Facade] checkPasswordAndCommunityType")
    @Test
    void givenPasswordAndCommunityType_whenCheckingPasswordAndCommunity_thenChecksSuccessfully() {
        // given

        // when
        communityFacade.checkPasswordAndCommunityType("1234", false);
        communityFacade.checkPasswordAndCommunityType(null, true);

        // then

    }

    @DisplayName("[Facade] checkPasswordAndCommunityType - 맞지 않는 경우")
    @Test
    void givenInvalidPasswordAndCommunityType_whenCheckingPasswordAndCommunity_thenThrowsBadPasswordAndCommunityTypeException() {
        // given

        // when and then
        assertThrows(BadPasswordAndCommunityTypeException.class, () ->
                communityFacade.checkPasswordAndCommunityType("1234", true));
        assertThrows(BadPasswordAndCommunityTypeException.class, () ->
                communityFacade.checkPasswordAndCommunityType(null, false));
    }
}