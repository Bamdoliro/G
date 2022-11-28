package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.chat.service.RoomService;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DdoServiceTest {

    @InjectMocks private DdoService ddoService;

    @Mock private DdoRepository ddoRepository;
    @Mock private UserFacade userFacade;
    @Mock private CommunityFacade communityFacade;
    @Mock private DdoFacade ddoFacade;
    @Mock private RoomService roomService;

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

    @DisplayName("[DDO] 게시물 생성")
    @Test
    public void createDdoTest() {
        // Given
        CreateDdoRequestDto request = CreateDdoRequestDto.builder()
                .title("제목입니다.")
                .content("내용입니다. 내용입니다. 내용입니다.")
                .capacity(2)
                .communityId(1L)
                .build();

        given(ddoRepository.save(any())).willReturn(ddo);
        given(userFacade.getCurrentUser()).willReturn(user);
        given(communityFacade.findCommunityById(anyLong())).willReturn(community);
        ArgumentCaptor<Ddo> captor = ArgumentCaptor.forClass(Ddo.class);
        willDoNothing().given(roomService).createRoom(anyString());


        // When
        ddoService.savePost(request);

        // Then
        verify(ddoRepository, times(1)).save(captor.capture());
        verify(roomService, times(1)).createRoom(request.getTitle());

        Ddo savedDdo = captor.getValue();
        assertEquals("제목입니다.", savedDdo.getTitle());
        assertEquals("내용입니다. 내용입니다. 내용입니다.", savedDdo.getContent());
        assertEquals(2, savedDdo.getCapacity());
        assertEquals(communityFacade.findCommunityById(1L), savedDdo.getCommunity());
    }

    @DisplayName("[DDO] 게시물의 id값을 넘겨주면 해당 게시물의 상세 페이지 조회")
    @Test
    void getDdoDetailTest() {
        // Given
        given(ddoFacade.findDdoById(anyLong())).willReturn(ddo);

        // When
        DdoDetailResponseDto response = ddoService.getDdoDetail(anyLong());

        // Then
        verify(ddoFacade, times(1)).findDdoById(anyLong());

        assertEquals("제목입니다.", response.getTitle());
        assertEquals("내용입니다. 내용입니다. 내용입니다.", response.getContent());
        assertEquals(2, response.getCapacity());
    }
}