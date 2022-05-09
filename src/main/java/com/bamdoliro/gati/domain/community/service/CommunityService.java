package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.presentation.dto.request.CreateCommunityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Transactional
    public void createCommunity(CreateCommunityRequestDto dto) {
        communityRepository.save(createCommunityFromDto(dto));
    }

    private Community createCommunityFromDto(CreateCommunityRequestDto dto) {
        return Community.builder()
                .name(dto.getName())
                .introduction(dto.getIntroduction())
                .numberOfPeople(dto.getNumberOfPeople())
                .isPublic(dto.getIsPublic())
                .build();
    }
}
