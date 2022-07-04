package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_like_table")
public class BoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_like_id")
    private Long boardLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User liker;

    @Builder
    public BoardLike (Board board, User user) {
        this.board = board;
        this.liker = user;
    }

    public static BoardLike of(Board board, User user) {
        return BoardLike.builder()
                .board(board)
                .user(user)
                .build();
    }

    public static BoardLike getBoardLike(Board board, User user) {
        BoardLike like = BoardLike.of(board, user);
        board.getLikes().add(like);
        user.getLikes().add(like);
        return like;
    }
}
