package com.bamdoliro.gati.domain.board.domain;


import com.bamdoliro.gati.domain.board.domain.type.board.BoardStatus;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_board")
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", length = 4000, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BoardStatus boardStatus;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Report> reportList = new ArrayList<>();

    @Builder
    public Board(Community community, User writer, String title, String content, List<String> images) {
        this.community = community;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.images = images;
        this.boardStatus = BoardStatus.EXISTED;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void deletePost() {
        this.boardStatus = BoardStatus.DELETED;
    }
}
