package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.ddo.Status;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DdoService {

    private final DdoRepository doRepository;
    private final UserFacade userFacade;
    private final DdoFacade ddoFacade;
    private final CommunityFacade communityFacade;
    private final MemberFacade memberFacade;

    @Transactional
    public void post(CreateDdoRequestDto request) {
        User user = userFacade.getCurrentUser();
        Community community = communityFacade.findCommunityById(request.getCommunityId());

        Ddo ddo = request.toEntity();
        ddo.setRelation(user, community);

        doRepository.save(ddo);
    }

    @Transactional(readOnly = true)
    public DdoDetailResponseDto getDoDetail(Long id) {
        return DdoDetailResponseDto.of(ddoFacade.findById(id));
    }

}
