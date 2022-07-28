package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.DdoJoin;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoJoinRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DdoJoinServiceTest {

    @Mock private DdoFacade ddoFacade;
    @Mock private UserFacade userFacade;
    @Mock private DdoJoinRepository ddoJoinRepository;
    @Mock private MemberFacade memberFacade;

    @InjectMocks private DdoJoinService ddoJoinService;

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
            .maxNumber(2)
            .community(community)
            .writer(user)
            .status(DdoStatus.OPEN)
            .build();

    DdoJoin ddoJoin = DdoJoin.createDoJoin(ddo, user);

    @DisplayName("[DDO] 게시물에 참여하기")
    @Test
    void joinTest() {
        // Given
        given(ddoFacade.findDdoById(anyLong())).willReturn(ddo);
        given(userFacade.getCurrentUser()).willReturn(user);
        given(ddoJoinRepository.save(any())).willReturn(ddoJoin);

        ArgumentCaptor<DdoJoin> captor = ArgumentCaptor.forClass(DdoJoin.class);

        // When
        ddoJoinService.ddoJoin(anyLong());

        // Then

        verify(ddoJoinRepository, times(1))
                .save(captor.capture());

        DdoJoin savedDdoJoin = captor.getValue();

        assertEquals(ddo, savedDdoJoin.getDdo());
        assertEquals(user, savedDdoJoin.getJoiner());
    }
}