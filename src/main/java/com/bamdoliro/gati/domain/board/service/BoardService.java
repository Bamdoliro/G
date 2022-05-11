package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoard;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailResponse;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    .boardType(post.getBoardType())
                    .createdAt(post.getCreatedAt())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Transactional
    public BoardResponse savePost(CreateBoard dto) {
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
                .title(board.getTitle())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .build();

        return response;
    }

    @Transactional
    public BoardDetailResponse getDetail(Long id) {
        Board post = boardRepository.findById(id).orElseThrow(null);
        return BoardDetailResponse.builder()
                .writer(post.getWriter())
                .boardType(post.getBoardType())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }


    @Transactional
    public ArrayList<BoardResponse> getDDO() {
        ArrayList<Board> ddos = boardRepository.findAllByBoardType(BoardType.DDO);
        ArrayList<BoardResponse> responses = new ArrayList<>();
        for (Board ddo : ddos) {
            responses.add(
                    BoardResponse.builder()
                            .writer(ddo.getWriter())
                            .boardType(ddo.getBoardType())
                            .title(ddo.getTitle())
                            .createdAt(ddo.getCreatedAt())
                            .build()
            );
        }

        return responses;
    }

    public ArrayList<BoardResponse> getGENERAL() {
        ArrayList<Board> generals = boardRepository.findAllByBoardType(BoardType.GENERAL);
        ArrayList<BoardResponse> responses = new ArrayList<>();
        for (Board general : generals) {
            responses.add(
                    BoardResponse.builder()
                            .writer(general.getWriter())
                            .boardType(general.getBoardType())
                            .title(general.getTitle())
                            .createdAt(general.getCreatedAt())
                            .build()
            );
        }

        return responses;
    }
}
