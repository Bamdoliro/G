package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardListResponseDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class  BoardController {

    private final BoardService boardService;

    @PostMapping
    public void savePost(@RequestBody @Valid CreateBoardRequestDto request) {
        boardService.savePost(request);
    }

    @GetMapping("/{id}")
    public BoardDetailDto getDetail(@PathVariable Long id) {
        return boardService.getDetail(id);
    }

    @PutMapping("/{id}")
    public void updatePost(
            @RequestBody @Valid UpdateBoardRequestDto request,
            @PathVariable Long id
    ) {
        boardService.updatePost(request, id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
    }

    @GetMapping("/community/{community-id}")
    public BoardListResponseDto getCommunityPosts(@PathVariable(name = "community-id") Long communityId) {
        return boardService.getCommunityPosts(communityId);
    }
}
