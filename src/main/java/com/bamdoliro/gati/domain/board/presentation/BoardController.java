package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.BoardRequest;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponse;
import com.bamdoliro.gati.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{writer}")
    public ArrayList<BoardResponse> getPostsByWriter(
            @PathVariable String writer
    ) {
        return boardService.getPostsByWriter(writer);
    }

    @PostMapping("/savePost")
    public BoardResponse posting(
            @RequestBody @Valid BoardRequest dto
    ) {
        log.info(dto.getContent());
        return boardService.savePost(dto);
    }



}
