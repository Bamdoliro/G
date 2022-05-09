package com.bamdoliro.gati.domain.board.domain;


import com.bamdoliro.gati.domain.board.domain.type.BoardType;
import com.bamdoliro.gati.domain.board.domain.type.Status;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "board_table")
public class Board extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    // Todo : 나중에 커뮤니티 맵핑
//    private Long communityId;

    // Todo :  나중에 유저 맵핑
//    private String writer;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_type", length = 7, nullable = false)
    private BoardType boardType;

    @Column(name = "content", columnDefinition = "TEXT", length = 1000, nullable = false)
    private String content;

    @Column(name = "status", length = 7, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Board (String title, BoardType boardType, String content) {
        this.title = title;
        this.boardType = boardType;
        this.content = content;
    }
}
