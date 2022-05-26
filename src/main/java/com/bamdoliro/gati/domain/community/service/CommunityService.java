package com.bamdoliro.gati.domain.community.service;

import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.type.Authority;
import com.bamdoliro.gati.domain.community.exception.BadCreateCommunityRequestException;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.presentation.dto.request.CreateCommunityRequestDto;
import com.bamdoliro.gati.global.utils.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberService memberService;
    private final CommunityFacade communityFacade;

    @Transactional
    public void createCommunity(CreateCommunityRequestDto dto) {
        validateByCommunityType(dto);

        memberService.joinCommunity(
                communityRepository.save(
                        dto.toEntity(createUniqueCode())
                ).getId(),
                Authority.LEADER
        );
    }

    private void validateByCommunityType(CreateCommunityRequestDto dto) {
        if ((dto.getPassword() != null && !dto.getIsPublic()) ||
                (dto.getPassword() == null && dto.getIsPublic()));
        else {
            throw BadCreateCommunityRequestException.EXCEPTION;
        }
    }

    private String createUniqueCode() {
        String code = RandomCodeUtil.make(6);
        while (!communityFacade.checkCode(code)) {
            code = RandomCodeUtil.make(6);
        }

        return code;
    }
}
