package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponseDto;
import com.bamdoliro.gati.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 특정 유저의 게시글 전부 보기

    @PostMapping("/save")
    public void savePost(
            @RequestBody @Valid CreateBoardRequestDto request
    ) {
        boardService.savePost(request);
    }

    @GetMapping("/detail/{id}")
    public BoardDetailDto getDetail(
            @PathVariable(name = "id") Long id
    ) {
        return boardService.getDetail(id);
    }


}
