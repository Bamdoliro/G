package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final CommunityFacade communityFacade;

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

    @Transactional
    public void updatePost(UpdateBoardRequest request) {
        Board board = boardFacade.findBoardById(request.getId());
        board.updatePost(request.getTitle(), request.getContent());
    }

}
