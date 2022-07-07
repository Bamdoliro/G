package com.bamdoliro.gati.domain.board.presentation;


import com.bamdoliro.gati.domain.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 좋아요
    @PutMapping("/{boardId}/like")
    public void like(
            @PathVariable Long boardId
    ) {
        likeService.like(boardId);
    }

    // 좋아요 취소
    @DeleteMapping("/{boardId}/like")
    public void cancelLike(
            @PathVariable Long boardId
    ) {
        likeService.cancelLike(boardId);
    }

}
