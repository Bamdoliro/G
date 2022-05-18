package com.bamdoliro.gati.domain.community.domain;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import com.bamdoliro.gati.global.utils.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Community extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String introduction;

    @Column(nullable = false)
    private int numberOfPeople;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(length = 1, nullable = false)
    private Boolean isPublic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Community(String name, String introduction, int numberOfPeople, Boolean isPublic) {
        this.name = name;
        this.introduction = introduction;
        this.numberOfPeople = numberOfPeople;
        this.isPublic = isPublic;
    }
}
