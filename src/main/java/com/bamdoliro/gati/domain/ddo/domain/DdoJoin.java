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
@Table(name = "tbl_ddo_join")
public class DdoJoin {

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
    public DdoJoin(Ddo ddo, User joiner) {
        this.ddo = ddo;
        this.joiner = joiner;
    }

    public static DdoJoin createDoJoin(Ddo ddo, User joiner) {

        DdoJoin ddoJoin = DdoJoin.builder()
                .ddo(ddo)
                .joiner(joiner)
                .build();

        ddo.getDdoJoinList().add(ddoJoin);
        joiner.getDdoJoinList().add(ddoJoin);

        return ddoJoin;
    }
}
