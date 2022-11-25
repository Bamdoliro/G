package com.bamdoliro.gati.domain.ddo.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_recommendation")
public class Recommendation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ddo_id")
    private Ddo ddo;

    public static Recommendation createDdo(User user, Ddo ddo) {
        Recommendation recommendation = new Recommendation();

        recommendation.user = user;
        user.getRecommendList().add(recommendation);

        recommendation.ddo = ddo;
        ddo.getRecommendList().add(recommendation);

        return recommendation;
    }
}
