package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.board.BoardStatus;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardListResponseDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final CommunityFacade communityFacade;
    private final MemberFacade memberFacade;

    @Transactional
    public void savePost(CreateBoardRequestDto request) {
        Board board = request.toEntity(
                userFacade.getCurrentUser(),
                communityFacade.findCommunityById(request.getCommunityId())
        );
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public BoardDetailDto getDetail(Long id) {
        Board board = boardFacade.findBoardById(id);
        return BoardDetailDto.of(board);
    }

    @Transactional
    public void updatePost(UpdateBoardRequestDto request, Long id) {
        Board board = boardFacade.findBoardById(id);
        board.updatePost(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deletePost(Long id) {
        Board board = boardFacade.findBoardById(id);
        board.deletePost();
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getCommunityPosts(Long communityId) {
        Community community = communityFacade.findCommunityById(communityId);
        memberFacade.checkMember(userFacade.getCurrentUser(), community);

        return new BoardListResponseDto(
                boardFacade.findBoardsByCommunityAndStatus(community, BoardStatus.EXISTED)
                        .stream().map(BoardResponseDto::of).collect(Collectors.toList())
        );
    }

}
