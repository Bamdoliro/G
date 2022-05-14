package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.bamdoliro.gati.domain.board.presentation.dto.request.SaveBoardRequest;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailResponse;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public BoardResponse savePost(SaveBoardRequest dto) {
        boardRepository.save(dto.createBoardFromSaveBoardRequest());
        BoardResponse response = BoardResponse.builder()
                .boardType(dto.getBoardType())
                .title(dto.getTitle())
                .writer(dto.getWriter())
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

    @Transactional
    public List<String> getTime() {
        List<Board> posts = boardRepository.findAll();
        List<String> times = new ArrayList<>();

        for(Board board : posts ) {
            times.add((board.getCreatedAt()).toString());
        }
        return times;
    }
}
