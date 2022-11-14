package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.Recommendation;
import com.bamdoliro.gati.domain.ddo.domain.repository.RecommendationRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.facade.RecommendationFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @InjectMocks
    private RecommendationService recommendationService;

    @Mock private RecommendationRepository recommendationRepository;
    @Mock private RecommendationFacade recommendationFacade;
    @Mock private DdoFacade ddoFacade;
    @Mock private UserFacade userFacade;


    Community community = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

    User user = User.builder()
            .email("gati@bamdoliro.com")
            .name("김가티")
            .password("12345678910")
            .authority(Authority.ROLE_USER)
            .gender(Gender.FEMALE)
            .birth(LocalDate.of(2022,2,2))
            .status(UserStatus.NOT_VERIFIED)
            .build();

    Ddo ddo = Ddo.builder()
            .title("제목입니다.")
            .content("내용입니다. 내용입니다. 내용입니다.")
            .capacity(2)
            .community(community)
            .writer(user)
            .status(DdoStatus.OPEN)
            .build();

    @DisplayName("추천 누르기")
    @Test
    void givenDdoId_whenRecommend_thenCreateRecommendation() {
        // Given
        final int beforeNumberOfRecommendation = ddo.getRecommendList().size();

        given(userFacade.getCurrentUser()).willReturn(user);
        given(ddoFacade.findDdoById(anyLong())).willReturn(ddo);

        ArgumentCaptor<Recommendation> captor = ArgumentCaptor.forClass(Recommendation.class);

        // When
        recommendationService.addRecommendation(1L);

        // Then
        verify(recommendationFacade, times(1))
                .validationRecommendation(any(), any());

        verify(recommendationRepository, times(1))
                .save(captor.capture());

        Recommendation recommendation = captor.getValue();

        assertEquals(beforeNumberOfRecommendation, ddo.getRecommendList().size()-1);
        assertEquals(user, recommendation.getUser());
        assertEquals(ddo, recommendation.getDdo());
    }

    @DisplayName("추천하기 취소")
    @Test
    void givenDdoId_whenCancelRecommend_thenRemoveRecommendation() {
        // Given
        given(userFacade.getCurrentUser()).willReturn(user);
        given(ddoFacade.findDdoById(anyLong())).willReturn(ddo);

        // When
        recommendationService.removeRecommendation(1L);

        // Then
        verify(recommendationRepository, times(1))
                .deleteByUserAndDdo(any(), any());
    }
}