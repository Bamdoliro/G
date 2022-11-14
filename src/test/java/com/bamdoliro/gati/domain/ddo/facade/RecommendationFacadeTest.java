package com.bamdoliro.gati.domain.ddo.facade;

import com.bamdoliro.gati.domain.ddo.domain.repository.RecommendationRepository;
import com.bamdoliro.gati.domain.ddo.exception.AlreadyRecommendException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecommendationFacadeTest {

    @InjectMocks private RecommendationFacade recommendationFacade;

    @Mock private RecommendationRepository recommendationRepository;

    @DisplayName("추천 유효성 검증 성공")
    @Test
    void givenUserAndDdo_whenValidateRecommendation_returnValidationSuccessfully() {
        // Given
        given(recommendationRepository.existsByUserAndDdo(any(), any())).willReturn(false);

        // When
        recommendationFacade.validationRecommendation(any(), any());

        // Then
        verify(recommendationRepository, times(1))
                .existsByUserAndDdo(any(), any());
    }

    @DisplayName("추천 유효성 검증 실패")
    @Test
    void givenUserAndDdo_whenValidateRecommendation_returnThrowAlreadyRecommendException() {
        // Given
        given(recommendationRepository.existsByUserAndDdo(any(), any())).willReturn(true);

        // When & Then
        assertThrows(AlreadyRecommendException.class,
                () -> recommendationFacade.validationRecommendation(any(), any()));

        verify(recommendationRepository, times(1))
                .existsByUserAndDdo(any(), any());
    }
}