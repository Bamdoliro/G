package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @InjectMocks
    private CommunityService communityService;

    @Mock
    private CommunityRepository communityRepository;

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
        ArgumentCaptor<Community> captor = ArgumentCaptor.forClass(Community.class);

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
        Community savedCommunity = captor.getValue();
        assertEquals("우리집", savedCommunity.getName());
        assertEquals("킄", savedCommunity.getIntroduction());
        assertEquals(100, savedCommunity.getNumberOfPeople());
        assertEquals(true, savedCommunity.getIsPublic());
    }
}