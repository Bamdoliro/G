package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.service.CommunityService;
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
    private final CommunityRepository communityRepository;
    private final UserService userService;

    @Transactional
    public void savePost(CreateBoardRequestDto request) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .community(
                        communityRepository.findById(request.getCommunityId())
                                .orElseThrow(NullPointerException::new)
                )
                .writer(userService.getCurrentUser())
                .build();
        boardRepository.save(board);
    }

    @Transactional
    public BoardDetailDto getDetail(Long id) {
        Board findBoard = boardRepository.findById(id)
                        .orElseThrow(NullPointerException::new);
        return BoardDetailDto.builder()
                .writer(findBoard.getWriter().getName())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .build();
    }
}
