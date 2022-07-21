package com.bamdoliro.gati.domain.ddo.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "join_table")
public class Join {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User joiner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ddo_id")
    private Ddo ddo;

    @Builder
    public Join(Ddo ddo, User joiner) {
        this.ddo = ddo;
        this.joiner = joiner;
    }

    public static Join createDoJoin(Ddo ddo, User joiner) {

        Join join = Join.builder()
                .ddo(ddo)
                .joiner(joiner)
                .build();

        ddo.getJoinList().add(join);
        joiner.getJoinList().add(join);

        return join;
    }
}
