package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "do_recommend_table")
public class DoRecommend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_recomend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "do_post_id")
    private DoPost doPost;

}
