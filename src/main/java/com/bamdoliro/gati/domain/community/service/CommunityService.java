package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.presentation.dto.request.CreateCommunityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberService memberService;

    @Transactional
    public void createCommunity(CreateCommunityRequestDto dto) {
        memberService.joinCommunity(
                communityRepository.save(dto.toEntity()).getId(),
                Authority.LEADER
        );
    }
}
