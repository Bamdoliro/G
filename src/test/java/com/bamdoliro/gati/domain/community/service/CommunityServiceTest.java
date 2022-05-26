package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.presentation.dto.request.CreateCommunityRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @InjectMocks
    private CommunityService communityService;

    @Mock private CommunityRepository communityRepository;
    @Mock private MemberService memberService;
    @Mock private CommunityFacade communityFacade;

    private final Community defaultCommunity = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

    @DisplayName("[Service] Community 생성")
    @Test
    void givenCreateCommunityRequestDto_whenCreatingCommunity_thenCreatesCommunity() {
        // given
        given(communityRepository.save(any())).willReturn(defaultCommunity);
        given(communityFacade.checkCode(anyString())).willReturn(true);
        ArgumentCaptor<Community> captor = ArgumentCaptor.forClass(Community.class);
        willDoNothing().given(memberService).joinCommunity(defaultCommunity.getId(), Authority.LEADER);

        // when
        communityService.createCommunity(
                new CreateCommunityRequestDto(
                        "우리집",
                        "킄",
                        100,
                        true
                )
        );

        // then
        verify(communityRepository, times(1)).save(captor.capture());
        verify(memberService, times(1)).joinCommunity(defaultCommunity.getId(), Authority.LEADER);
        Community savedCommunity = captor.getValue();
        assertEquals("우리집", savedCommunity.getName());
        assertEquals("킄", savedCommunity.getIntroduction());
        assertEquals(100, savedCommunity.getNumberOfPeople());
        assertEquals(6, savedCommunity.getCode().length());
        assertEquals(true, savedCommunity.getIsPublic());
    }
}