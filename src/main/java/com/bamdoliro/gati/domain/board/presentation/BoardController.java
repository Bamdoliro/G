package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    // 게시물 저장
    @PostMapping
    public void savePost(
            @RequestBody @Valid CreateBoardRequestDto request
    ) {
        boardService.savePost(request);
    }

    // 게시물 디테일
    @GetMapping("/detail/{id}")
    public BoardDetailDto getDetail(
            @PathVariable(name = "id") Long id
    ) {
        return boardService.getDetail(id);
    }

    // 게시물 수정
    @PutMapping
    public void updatePost(
            @RequestBody @Valid UpdateBoardRequestDto request
    ) {
        boardService.updatePost(request);
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable Long id
    ) {
        boardService.deletePost(id);
    }

    // 커뮤니티 전체 게시물 조회
    @GetMapping("/community/{communityId}")
    public List<BoardResponseDto> getCommunityPosts(
            @PathVariable Long communityId
    ) {
        return boardService.getCommunityPosts(communityId);
    }

}
