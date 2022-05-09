package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.bamdoliro.gati.domain.board.presentation.dto.request.BoardRequest;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public ArrayList<BoardResponse> getPostsByWriter(String writer) {
        ArrayList<Board> posts = boardRepository.findAllByWriter(writer);
        ArrayList<BoardResponse> responses = new ArrayList<>();
        for (Board post : posts) {
            BoardResponse response = BoardResponse.builder()
                    .writer(post.getWriter())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .boardType(post.getBoardType())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Transactional
    public BoardResponse savePost(BoardRequest dto) {
        Board board = Board.builder()
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .boardType(BoardType.DDO)
                .status(Status.EXISTED)
                .build();
        boardRepository.save(board);
        BoardResponse response = BoardResponse.builder()
                .boardType(board.getBoardType())
                .content(board.getContent())
                .title(board.getTitle())
                .writer(board.getWriter())
                .build();

        return response;
    }
}
