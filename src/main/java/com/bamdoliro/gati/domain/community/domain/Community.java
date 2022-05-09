package com.bamdoliro.gati.domain.community.domain;

import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import com.bamdoliro.gati.global.utils.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "community_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Builder
    public Community(String name, String introduction, int numberOfPeople, Boolean isPublic) {
        this.name = name;
        this.introduction = introduction;
        this.numberOfPeople = numberOfPeople;
        this.isPublic = isPublic;
    }
}
