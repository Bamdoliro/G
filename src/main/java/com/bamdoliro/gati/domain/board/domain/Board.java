package com.bamdoliro.gati.domain.board.domain;


import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_table")
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", length = 1000, nullable = false)
    private String content;

    @Builder
    public Board(Community community, User writer, String title, String content) {
        this.community = community;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
