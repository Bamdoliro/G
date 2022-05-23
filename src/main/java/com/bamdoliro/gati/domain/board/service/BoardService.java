package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.service.CommunityService;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.domain.user.service.UserService;
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

    // 게시물 게시
    @Transactional
    public void savePost(CreateBoardRequestDto request) {
        Board board = request.toEntity();
        boardRepository.save(board);
    }

    // 게시물 디테일 보기
    @Transactional(readOnly = true)
    public BoardDetailDto getDetail(Long id) {
        Board board = boardFacade.findBoardById(id);
        return BoardDetailDto.of(board);
    }
}
