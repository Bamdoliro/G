package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.repository.RoomMemberRepository;
import com.bamdoliro.gati.domain.chat.presentation.dto.response.RoomResponseDto;
import com.bamdoliro.gati.domain.chat.service.RoomService;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoJoinRepository;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoListResponseDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoResponseDto;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DdoService {

    private final DdoRepository ddoRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final UserFacade userFacade;
    private final DdoFacade ddoFacade;
    private final CommunityFacade communityFacade;
    private final RoomService roomService;
    private final DdoJoinRepository ddoJoinRepository;

    @Transactional
    public RoomResponseDto savePost(CreateDdoRequestDto request) {
        Ddo ddo = request.toEntity();
        ddo.setRelation(userFacade.getCurrentUser(), communityFacade.findCommunityById(request.getCommunityId()));

        ddoRepository.save(ddo);
        Room room = roomService.createRoom(ddo.getTitle());

        return RoomResponseDto.of(room, getRoomNumberOfMembers(room));
    }

    private int getRoomNumberOfMembers(Room room) {
        return roomMemberRepository.countByRoom(room);
    }

    @Transactional(readOnly = true)
    public DdoListResponseDto getDdoList(Long communityId) {
        communityFacade.checkCommunityExists(communityId);

        return new DdoListResponseDto(
                ddoFacade.findDdoOrderByRecommendationSize(communityId).stream()
                        .map(DdoResponseDto::of)
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public DdoDetailResponseDto getDdoDetail(Long id) {
        Ddo ddo = ddoFacade.findDdoById(id);
        boolean isJoin = ddoJoinRepository.existsByDdoAndJoiner(ddo, userFacade.getCurrentUser());
        return DdoDetailResponseDto.of(ddo, isJoin);
    }
}
