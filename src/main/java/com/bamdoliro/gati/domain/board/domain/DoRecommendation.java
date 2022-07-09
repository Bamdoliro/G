package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "do_recommendation_table")
public class DoRecommendation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_recommendation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommender_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "do_post_id")
    private DoPost doPost;

}
