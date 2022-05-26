package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
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

    @PutMapping
    public void updatePost(
            @RequestBody @Valid UpdateBoardRequest request
    ) {
        boardService.updatePost(request);
    }


}
