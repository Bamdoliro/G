package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoard;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailResponse;
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

    // 특정 유저의 게시글 전부 보기
    @GetMapping("/{writer}")
    public ArrayList<BoardResponse> getPostsByWriter(
            @PathVariable String writer
    ) {
        return boardService.getPostsByWriter(writer);
    }

    // 게시물 작성
    @PostMapping("/savePost")
    public BoardResponse posting(
            @RequestBody @Valid CreateBoard dto
    ) {
        log.info(dto.getContent());
        return boardService.savePost(dto);
    }

    @GetMapping("/getDetail/{id}")
    public BoardDetailResponse getDetail(
            @PathVariable Long id
    ) {
        return boardService.getDetail(id);
    }

    @GetMapping("/getPosts/DDO")
    public ArrayList<BoardResponse> getDDO() {
        return boardService.getDDO();
    }

    @GetMapping("/getPosts/GENERAL")
    public ArrayList<BoardResponse> getGENERAL() {
        return boardService.getGENERAL();
    }



}
