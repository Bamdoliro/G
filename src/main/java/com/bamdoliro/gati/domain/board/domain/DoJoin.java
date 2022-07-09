package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "do_join_table")
public class DoJoin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_join_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User joiner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "do_post_id")
    private DoPost doPost;
}
