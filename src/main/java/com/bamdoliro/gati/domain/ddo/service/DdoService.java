package com.bamdoliro.gati.domain.ddo.service;

import  com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DdoService {

    private final DdoRepository ddoRepository;
    private final UserFacade userFacade;
    private final DdoFacade ddoFacade;
    private final CommunityFacade communityFacade;

    @Transactional
    public void savePost(CreateDdoRequestDto request) {

        Ddo ddo = request.toEntity();
        ddo.setRelation(userFacade.getCurrentUser(), communityFacade.findCommunityById(request.getCommunityId()));

        ddoRepository.save(ddo);
    }

    @Transactional(readOnly = true)
    public DdoDetailResponseDto getDoDetail(Long id) {
        return DdoDetailResponseDto.of(ddoFacade.findDdoById(id));
    }

}
