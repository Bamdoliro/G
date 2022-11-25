package com.bamdoliro.gati.domain.ddo.facade;

import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoJoinRepository;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.ddo.exception.AlreadyJoinException;
import com.bamdoliro.gati.domain.ddo.exception.DdoNotFoundException;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DdoFacadeTest {

    @InjectMocks private DdoFacade ddoFacade;

    @Mock private DdoRepository ddoRepository;
    @Mock private DdoJoinRepository ddoJoinRepository;

    Community community = Community.builder()
            .name("우리집")
            .introduction("킄")
            .capacity(100)
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


    @DisplayName("findDdoById - 성공")
    @Test
    void givenDdoId_whenFindDdo_thenReturnDdo() {
        // Given
        given(ddoRepository.findById(anyLong())).willReturn(Optional.of(ddo));

        // When
        Ddo findDdo = ddoFacade.findDdoById(anyLong());

        // Then
        assertEquals(ddo.getId(), findDdo.getId());
        assertEquals(ddo.getTitle(), findDdo.getTitle());
        assertEquals(ddo.getContent(), findDdo.getContent());
        assertEquals(ddo.getCapacity(), findDdo.getCapacity());
        assertEquals(ddo.getDdoJoinList(), findDdo.getDdoJoinList());
    }

    @DisplayName("findDdoById - 실패")
    @Test
    void givenDdoId_whenFindDdo_thenThrowBoardNotFoundException() {
        // Given
        given(ddoRepository.findById(anyLong())).willReturn(Optional.empty());

        // When & Then
       assertThrows(BoardNotFoundException.class, () -> ddoFacade.findDdoById(anyLong()));
    }

    @DisplayName("validationJoin - 성공")
    @Test
    void givenDdoAndUser_whenValidateJoin_thenValidateSuccessfully() {
        // Given
        given(ddoJoinRepository.existsByDdoAndJoiner(any(), any())).willReturn(false);

        // When
        ddoFacade.validationJoin(ddo, user);

        // Then
        verify(ddoJoinRepository, times(1))
                .existsByDdoAndJoiner(any(), any());
    }

    @DisplayName("validationJoin - 실패")
    @Test
    void givenDdoAndUser_whenValidateJoin_thenThrowAlreadyJoinException() {
        // Given
        given(ddoJoinRepository.existsByDdoAndJoiner(any(), any())).willReturn(true);

        // When & Then
        assertThrows(AlreadyJoinException.class,
                () ->ddoFacade.validationJoin(ddo, user));

        verify(ddoJoinRepository, times(1))
                .existsByDdoAndJoiner(any(), any());
    }


    @DisplayName("findDdoOrderByRecommendationSize - 성공")
    @Test
    void givenCommunityId_whenFindDdo_thenDdoListOrderByRecommendationSize() {
        // Given
        given(ddoRepository.findAllOrderByRecommendation(anyLong(), any()))
                .willReturn(Optional.of(List.of(ddo)));

        // When
        List<Ddo> list = ddoFacade.findDdoOrderByRecommendationSize(1L);

        // Then
        assertEquals("제목입니다.", list.get(0).getTitle());
        assertEquals("내용입니다. 내용입니다. 내용입니다.", list.get(0).getContent());
        assertEquals(2, list.get(0).getCapacity());
        assertEquals(DdoStatus.OPEN, list.get(0).getStatus());
        assertEquals(user, list.get(0).getWriter());
        assertEquals(community, list.get(0).getCommunity());


        verify(ddoRepository, times(1))
                .findAllOrderByRecommendation(any(), any());
    }

    @DisplayName("findDdoOrderByRecommendationSize - 실패")
    @Test
    void givenCommunityId_whenFindDdo_thenThrowDdoNotFoundException() {
        // Given
        given(ddoRepository.findAllOrderByRecommendation(anyLong(), any()))
                .willReturn(Optional.empty());

        // When & Then
        assertThrows(DdoNotFoundException.class,
                () -> ddoFacade.findDdoOrderByRecommendationSize(1L));

        verify(ddoRepository, times(1))
                .findAllOrderByRecommendation(anyLong(), any());
    }
}