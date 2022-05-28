package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final CommunityFacade communityFacade;
    private final MemberFacade memberFacade;

    // 게시물 게시
    @Transactional
    public void savePost(CreateBoardRequestDto request) {
        Board board = request.toEntity(
                userFacade.getCurrentUser(),
                communityFacade.findCommunityById(request.getCommunityId()));
        boardRepository.save(board);
    }

    // 게시물 디테일 보기
    @Transactional(readOnly = true)
    public BoardDetailDto getDetail(Long id) {
        Board board = boardFacade.findBoardById(id);
        return BoardDetailDto.of(board);
    }

    // 게시물 수정
    @Transactional
    public void updatePost(UpdateBoardRequestDto request) {
        Board board = boardFacade.findBoardById(request.getId());
        board.updatePost(request.getTitle(), request.getContent());
    }

    // 게시물 삭제
    @Transactional
    public void deletePost(Long id) {
        Board board = boardFacade.findBoardById(id);
        board.deletePost();
    }

    // 특정 커뮤니티의 게시물 전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getCommunityPosts(Long communityId) {
        Community community = communityFacade.findCommunityById(communityId);
        memberFacade.checkMember(userFacade.getCurrentUser(), community);

        List<Board> boards = boardFacade.findByCommunityAndStatus(community, Status.EXISTED);

        List<BoardResponseDto> responses = new ArrayList<>();
        boards.forEach(board -> responses.add(BoardResponseDto.of(board)));
        return responses;
    }
}
